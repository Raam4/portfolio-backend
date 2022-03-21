package com.raama.portfoliobackend.security.controller;

import com.raama.portfoliobackend.dto.Message;
import com.raama.portfoliobackend.security.dto.JwtDto;
import com.raama.portfoliobackend.security.dto.NewUser;
import com.raama.portfoliobackend.security.dto.UserLogin;
import com.raama.portfoliobackend.security.entity.Role;
import com.raama.portfoliobackend.security.entity.User;
import com.raama.portfoliobackend.security.enums.RoleName;
import com.raama.portfoliobackend.security.jwt.JwtProvider;
import com.raama.portfoliobackend.security.service.RoleService;
import com.raama.portfoliobackend.security.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleService roleService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/new")
    public ResponseEntity<?> create(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Message("Invalid fields."), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(newUser.getEmail())){
            return new ResponseEntity(new Message("Email already exists."), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUsername(newUser.getUsername())){
            return new ResponseEntity(new Message("Username already exists."), HttpStatus.BAD_REQUEST);
        }
        User user = new User(newUser.getEmail(), newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin")){
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Message("User saved."), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Message("Invalid fields."), HttpStatus.BAD_REQUEST);
        }
        if(!userService.existsByUsername(userLogin.getUsername())){
            return new ResponseEntity(new Message("Username doesn't exists."), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}