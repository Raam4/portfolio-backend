package com.raama.portfoliobackend.repository;

import com.raama.portfoliobackend.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer>{
    
}
