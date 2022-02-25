package com.raama.portfoliobackend.service;

import com.raama.portfoliobackend.entity.Project;
import com.raama.portfoliobackend.repository.ProjectRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public List<Project> list(){
        return projectRepository.findAll();
    }

    public Optional<Project> getOne(int id){
        return projectRepository.findById(id);
    }

    public void  save(Project project){
        projectRepository.save(project);
    }

    public void delete(int id){
        projectRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return projectRepository.existsById(id);
    }
}