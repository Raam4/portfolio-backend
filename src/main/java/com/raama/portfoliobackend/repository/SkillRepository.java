package com.raama.portfoliobackend.repository;

import com.raama.portfoliobackend.entity.Skill;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{
    Optional<Skill> findByName(String name);
    boolean existsByName(String name);
}
