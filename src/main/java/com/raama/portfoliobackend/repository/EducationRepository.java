package com.raama.portfoliobackend.repository;

import com.raama.portfoliobackend.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer>{
    
}
