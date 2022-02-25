package com.raama.portfoliobackend.service;

import com.raama.portfoliobackend.entity.Education;
import com.raama.portfoliobackend.repository.EducationRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public List<Education> list(){
        return educationRepository.findAll();
    }

    public Optional<Education> getOne(int id){
        return educationRepository.findById(id);
    }

    public void  save(Education education){
        educationRepository.save(education);
    }

    public void delete(int id){
        educationRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return educationRepository.existsById(id);
    }
}