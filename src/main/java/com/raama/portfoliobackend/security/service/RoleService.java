package com.raama.portfoliobackend.security.service;

import com.raama.portfoliobackend.security.entity.Role;
import com.raama.portfoliobackend.security.enums.RoleName;
import com.raama.portfoliobackend.security.repository.RoleRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    
    @Autowired
    RoleRepository roleRepository;
    
    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
    
    public void save(Role role){
        roleRepository.save(role);
    }
}
