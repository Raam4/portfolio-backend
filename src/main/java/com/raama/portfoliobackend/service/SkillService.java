package com.raama.portfoliobackend.service;

import com.raama.portfoliobackend.entity.Skill;
import com.raama.portfoliobackend.repository.SkillRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public List<Skill> list(){
        return skillRepository.findAll();
    }

    public Optional<Skill> getOne(int id){
        return skillRepository.findById(id);
    }

    public Optional<Skill> getByName(String name){
        return skillRepository.findByName(name);
    }

    public void  save(Skill skill){
        skillRepository.save(skill);
    }

    public void delete(int id){
        skillRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return skillRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return skillRepository.existsByName(name);
    }
}