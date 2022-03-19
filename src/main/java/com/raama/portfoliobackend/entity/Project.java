package com.raama.portfoliobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String techs;
    private String picLoc;
    private int dateYear;
    private String link;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column (columnDefinition = "text")
    private String description;
    
    @JsonIgnoreProperties("projects")
    @ManyToOne(optional = false)
    private Person person;
    
    public Project(){}

    public Project(String name, String techs, String picLoc, int dateYear, String link, String description, Person person) {
        this.name = name;
        this.techs = techs;
        this.picLoc = picLoc;
        this.dateYear = dateYear;
        this.link = link;
        this.description = description;
        this.person = person;
    }
}
