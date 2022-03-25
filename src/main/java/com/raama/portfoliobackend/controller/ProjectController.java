package com.raama.portfoliobackend.controller;

import com.raama.portfoliobackend.dto.Message;
import com.raama.portfoliobackend.dto.ProjectDto;
import com.raama.portfoliobackend.entity.Project;
import com.raama.portfoliobackend.service.ProjectService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "*")
public class ProjectController {
    
    @Autowired
    ProjectService projectService;

    @GetMapping("/list")
    public ResponseEntity<List<Project>> list(){
        List<Project> list = projectService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") int id){
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message("Project doesn't exists."), HttpStatus.NOT_FOUND);
        Project project = projectService.getOne(id).get();
        return new ResponseEntity(project, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProjectDto projectDto){
        if(StringUtils.isBlank(projectDto.getName()))
            return new ResponseEntity(new Message("Name is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getTechs()))
            return new ResponseEntity(new Message("Techs is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getImgUrl()))
            return new ResponseEntity(new Message("Image is mandatory."), HttpStatus.BAD_REQUEST);
        if(projectDto.getDateYear() <= 0 || projectDto.getDateYear() > 2022)
            return new ResponseEntity(new Message("Year is mandatory and it can\'t be greater than 2022."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getLink()))
            return new ResponseEntity(new Message("Link is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getDescription()))
            return new ResponseEntity(new Message("Description is mandatory."), HttpStatus.BAD_REQUEST);
        Project project = new Project(projectDto.getName(), projectDto.getTechs(),
                projectDto.getImgUrl(), projectDto.getDateYear(), projectDto.getLink(),
                projectDto.getDescription(), projectDto.getPerson());
        projectService.save(project);
        return new ResponseEntity(new Message("Project created."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProjectDto projectDto){
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message("Project doesn't exist"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(projectDto.getName()))
            return new ResponseEntity(new Message("Name is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getTechs()))
            return new ResponseEntity(new Message("Techs is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getImgUrl()))
            return new ResponseEntity(new Message("Image is mandatory."), HttpStatus.BAD_REQUEST);
        if(projectDto.getDateYear() <= 0 || projectDto.getDateYear() > 2022)
            return new ResponseEntity(new Message("Year is mandatory and it can\'t be greater than 2022."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getLink()))
            return new ResponseEntity(new Message("Link is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(projectDto.getDescription()))
            return new ResponseEntity(new Message("Description is mandatory."), HttpStatus.BAD_REQUEST);
        Project project = projectService.getOne(id).get();
        project.setName(projectDto.getName());
        project.setTechs(projectDto.getTechs());
        project.setImgUrl(projectDto.getImgUrl());
        project.setDateYear(projectDto.getDateYear());
        project.setLink(projectDto.getLink());
        project.setDescription(projectDto.getDescription());
        projectService.save(project);
        return new ResponseEntity(new Message("Project updated."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!projectService.existsById(id))
            return new ResponseEntity(new Message("Project doesn't exist."), HttpStatus.NOT_FOUND);
        projectService.delete(id);
        return new ResponseEntity(new Message("Project deleted."), HttpStatus.OK);
    }
}
