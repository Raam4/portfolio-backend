package com.raama.portfoliobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;

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
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String position;
    private String company;
    private String logoLoc;
    private String location;
    private Date dateStart;
    private Date dateEnd;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column (columnDefinition = "text")
    private String description;
    
    @JsonIgnoreProperties("experiences")
    @ManyToOne(optional = false)
    private Person person;
    
    public Experience(){}

    public Experience(String position, String company, String logoLoc, String location, Date dateStart, Date dateEnd, String description, Person person) {
        this.position = position;
        this.company = company;
        this.logoLoc = logoLoc;
        this.location = location;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.person = person;
    }
}
