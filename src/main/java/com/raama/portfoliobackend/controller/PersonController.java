package com.raama.portfoliobackend.controller;

import com.raama.portfoliobackend.dto.Message;
import com.raama.portfoliobackend.dto.PersonDto;
import com.raama.portfoliobackend.entity.Person;
import com.raama.portfoliobackend.service.PersonService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> list(){
        List<Person> list = personService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") int id){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message("Person doesn't exists."), HttpStatus.NOT_FOUND);
        Person person = personService.getOne(id).get();
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonDto personDto){
        if(StringUtils.isBlank(personDto.getFirstName()))
            return new ResponseEntity(new Message("First name is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLastName()))
            return new ResponseEntity(new Message("Last name is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getTitle()))
            return new ResponseEntity(new Message("Title is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLocation()))
            return new ResponseEntity(new Message("Location is mandatory."), HttpStatus.BAD_REQUEST);
        Person person = new Person(personDto.getFirstName(), personDto.getLastName(),
                personDto.getTitle(), personDto.getPicLoc(), personDto.getLocation(),
                personDto.getAbout());
        personService.save(person);
        return new ResponseEntity(new Message("Person created."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody PersonDto personDto){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message("Person doesn't exist"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(personDto.getTitle()))
            return new ResponseEntity(new Message("Title is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getLocation()))
            return new ResponseEntity(new Message("Location is mandatory."), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(personDto.getAbout()))
            return new ResponseEntity(new Message("About is mandatory."), HttpStatus.BAD_REQUEST);
        Person person = personService.getOne(id).get();
        person.setTitle(personDto.getTitle());
        person.setLocation(personDto.getLocation());
        person.setAbout(personDto.getAbout());
        personService.save(person);
        return new ResponseEntity(new Message("Person updated."), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!personService.existsById(id))
            return new ResponseEntity(new Message("Person doesn't exist."), HttpStatus.NOT_FOUND);
        personService.delete(id);
        return new ResponseEntity(new Message("Person deleted."), HttpStatus.OK);
    }
}