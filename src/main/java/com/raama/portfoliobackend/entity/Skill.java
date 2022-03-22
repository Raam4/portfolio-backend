package com.raama.portfoliobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String imgUrl;
    
    @JsonIgnoreProperties("skills")
    @ManyToOne(optional = false)
    private Person person;
    
    public Skill(){}

    public Skill(String name, String type, String imgUrl, Person person) {
        this.name = name;
        this.type = type;
        this.imgUrl = imgUrl;
        this.person = person;
    }
}
