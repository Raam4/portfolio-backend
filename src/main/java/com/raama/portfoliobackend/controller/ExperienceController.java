package com.raama.portfoliobackend.controller;

import com.raama.portfoliobackend.dto.ExperienceDto;
import com.raama.portfoliobackend.dto.Message;
import com.raama.portfoliobackend.entity.Experience;
import com.raama.portfoliobackend.service.ExperienceService;
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
@RequestMapping("/experience")
@CrossOrigin(origins = "*")
public class ExperienceController {
    
    @Autowired
    ExperienceService experienceService;

    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list(){
        List<Experience> list = experienceService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Message("Experience doesn't exists."), HttpStatus.NOT_FOUND);
        Experience experience = experienceService.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienceDto experienceDto){
        if(StringUtils.isBlank(experienceDto.getPosition()))
            return new ResponseEntity(new Message("Position is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getCompany()))
            return new ResponseEntity(new Message("Company is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getLocation()))
            return new ResponseEntity(new Message("Location is mandatory."), HttpStatus.BAD_REQUEST);
        if(experienceDto.getDateStart() == null)
            return new ResponseEntity(new Message("Date is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getDescription()))
            return new ResponseEntity(new Message("Description is mandatory."), HttpStatus.BAD_REQUEST);
        Experience experience = new Experience(experienceDto.getPosition(), experienceDto.getCompany(),
                experienceDto.getLogoLoc(), experienceDto.getLocation(), experienceDto.getDateStart(),
                experienceDto.getDateEnd(), experienceDto.getDescription(), experienceDto.getPerson());
        experienceService.save(experience);
        return new ResponseEntity(new Message("Experience created."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ExperienceDto experienceDto){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Message("Experience doesn't exist"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(experienceDto.getPosition()))
            return new ResponseEntity(new Message("Position is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getCompany()))
            return new ResponseEntity(new Message("Company is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getLocation()))
            return new ResponseEntity(new Message("Location is mandatory."), HttpStatus.BAD_REQUEST);
        if(experienceDto.getDateStart() == null)
            return new ResponseEntity(new Message("Date is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(experienceDto.getDescription()))
            return new ResponseEntity(new Message("Description is mandatory."), HttpStatus.BAD_REQUEST);
        Experience experience = experienceService.getOne(id).get();
        experience.setPosition(experienceDto.getPosition());
        experience.setCompany(experienceDto.getCompany());
        experience.setLogoLoc(experienceDto.getLogoLoc());
        experience.setLocation(experienceDto.getLocation());
        experience.setDateStart(experienceDto.getDateStart());
        experience.setDateEnd(experienceDto.getDateEnd());
        experience.setDescription(experienceDto.getDescription());
        experience.setPerson(experienceDto.getPerson());
        experienceService.save(experience);
        return new ResponseEntity(new Message("Experience updated."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!experienceService.existsById(id))
            return new ResponseEntity(new Message("Experience doesn't exist."), HttpStatus.NOT_FOUND);
        experienceService.delete(id);
        return new ResponseEntity(new Message("Experience deleted."), HttpStatus.OK);
    }
}
