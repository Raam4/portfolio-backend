package com.raama.portfoliobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String institution;
    private String degree;
    private String location;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    
    @JsonIgnoreProperties("educations")
    @ManyToOne(optional = false)
    private Person person;
    
    public Education(){}

    public Education(String title, String institution, String degree, String location, Date dateStart, Date dateEnd, String description) {
        this.title = title;
        this.institution = institution;
        this.degree = degree;
        this.location = location;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
