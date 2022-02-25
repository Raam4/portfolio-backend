package com.raama.portfoliobackend.service;

import com.raama.portfoliobackend.entity.Person;
import com.raama.portfoliobackend.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> list(){
        return personRepository.findAll();
    }

    public Optional<Person> getOne(int id){
        return personRepository.findById(id);
    }

    public void save(Person person){
        personRepository.save(person);
    }

    public void delete(int id){
        personRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return personRepository.existsById(id);
    }
}