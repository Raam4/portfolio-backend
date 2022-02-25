package com.raama.portfoliobackend.security.repository;

import com.raama.portfoliobackend.security.entity.Role;
import com.raama.portfoliobackend.security.enums.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRoleName(RoleName roleName);
}
