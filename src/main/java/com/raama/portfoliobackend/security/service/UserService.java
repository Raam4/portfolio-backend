package com.raama.portfoliobackend.security.service;

import com.raama.portfoliobackend.security.entity.User;
import com.raama.portfoliobackend.security.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    
    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
    
    public void save(User user){
        userRepository.save(user);
    }
}