package com.raama.portfoliobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String picLoc;
    private String location;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column (columnDefinition = "text")
    private String about;
    
    @JsonIgnoreProperties("person")
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Experience> experiences = new HashSet<>();
    
    @JsonIgnoreProperties("person")
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Education> educations = new HashSet<>();
    
    @JsonIgnoreProperties("person")
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();
    
    @JsonIgnoreProperties("person")
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    public Person() {
    }

    public Person(String firstName, String lastName, String title, String picLoc, String location, String about) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.picLoc = picLoc;
        this.location = location;
        this.about = about;
    }
    
    
}