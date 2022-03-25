package com.raama.portfoliobackend.controller;

import com.raama.portfoliobackend.dto.Message;
import com.raama.portfoliobackend.dto.SkillDto;
import com.raama.portfoliobackend.entity.Skill;
import com.raama.portfoliobackend.service.SkillService;
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
@RequestMapping("/skill")
@CrossOrigin(origins = "*")
public class SkillController {
    
    @Autowired
    SkillService skillService;

    @GetMapping("/list")
    public ResponseEntity<List<Skill>> list(){
        List<Skill> list = skillService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skill> getById(@PathVariable("id") int id){
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message("Skill doesn't exists."), HttpStatus.NOT_FOUND);
        Skill skill = skillService.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SkillDto skillDto){
        if(StringUtils.isBlank(skillDto.getName()))
            return new ResponseEntity(new Message("Name is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getType()))
            return new ResponseEntity(new Message("Type is mandatory."), HttpStatus.BAD_REQUEST);
        Skill skill = new Skill(skillDto.getName(), skillDto.getType(), skillDto.getImgUrl(), skillDto.getPerson());
        skillService.save(skill);
        return new ResponseEntity(new Message("Skill created."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody SkillDto skillDto){
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message("Skill doesn't exist"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(skillDto.getName()))
            return new ResponseEntity(new Message("Name is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getType()))
            return new ResponseEntity(new Message("Type is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(skillDto.getImgUrl()))
            return new ResponseEntity(new Message("Icon is mandatory."), HttpStatus.BAD_REQUEST);
        Skill skill = skillService.getOne(id).get();
        skill.setName(skillDto.getName());
        skill.setType(skillDto.getType());
        skill.setImgUrl(skillDto.getImgUrl());
        skillService.save(skill);
        return new ResponseEntity(new Message("Skill updated."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!skillService.existsById(id))
            return new ResponseEntity(new Message("Skill doesn't exist."), HttpStatus.NOT_FOUND);
        skillService.delete(id);
        return new ResponseEntity(new Message("Skill deleted."), HttpStatus.OK);
    }
}
