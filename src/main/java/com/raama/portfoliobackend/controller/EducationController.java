package com.raama.portfoliobackend.controller;

import com.raama.portfoliobackend.dto.EducationDto;
import com.raama.portfoliobackend.dto.Message;
import com.raama.portfoliobackend.entity.Education;
import com.raama.portfoliobackend.service.EducationService;
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
@RequestMapping("/education")
@CrossOrigin(origins = "*")
public class EducationController {
    
    @Autowired
    EducationService educationService;

    @GetMapping("/list")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = educationService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message("Education doesn't exists."), HttpStatus.NOT_FOUND);
        Education education = educationService.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducationDto educationDto){
        if(StringUtils.isBlank(educationDto.getTitle()))
            return new ResponseEntity(new Message("Title is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getInstitution()))
            return new ResponseEntity(new Message("Institution is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getDegree()))
            return new ResponseEntity(new Message("Degree is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getLocation()))
            return new ResponseEntity(new Message("Location is mandatory."), HttpStatus.BAD_REQUEST);
        if(educationDto.getDateStart() == null )
            return new ResponseEntity(new Message("Date is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getDescription()))
            return new ResponseEntity(new Message("Description is mandatory."), HttpStatus.BAD_REQUEST);
        Education education = new Education(educationDto.getTitle(), educationDto.getInstitution(),
                educationDto.getDegree(), educationDto.getLocation(), educationDto.getDateStart(),
                educationDto.getDateEnd(), educationDto.getDescription());
        educationService.save(education);
        return new ResponseEntity(new Message("Education created."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody EducationDto educationDto){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message("Education doesn't exist"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(educationDto.getTitle()))
            return new ResponseEntity(new Message("Title is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getInstitution()))
            return new ResponseEntity(new Message("Institution is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getDegree()))
            return new ResponseEntity(new Message("Degree is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getLocation()))
            return new ResponseEntity(new Message("Location is mandatory."), HttpStatus.BAD_REQUEST);
        if(educationDto.getDateStart() == null )
            return new ResponseEntity(new Message("Date is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(educationDto.getDescription()))
            return new ResponseEntity(new Message("Description is mandatory."), HttpStatus.BAD_REQUEST);
        Education education = educationService.getOne(id).get();
        education.setTitle(educationDto.getTitle());
        education.setInstitution(educationDto.getInstitution());
        education.setDegree(educationDto.getDegree());
        education.setLocation(educationDto.getLocation());
        education.setDateStart(educationDto.getDateStart());
        education.setDateEnd(educationDto.getDateEnd());
        education.setDescription(educationDto.getDescription());
        educationService.save(education);
        return new ResponseEntity(new Message("Education updated."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!educationService.existsById(id))
            return new ResponseEntity(new Message("Education doesn't exist."), HttpStatus.NOT_FOUND);
        educationService.delete(id);
        return new ResponseEntity(new Message("Education deleted."), HttpStatus.OK);
    }
}
