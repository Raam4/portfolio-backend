package com.raama.portfoliobackend.dto;

import javax.validation.constraints.NotBlank;

import com.raama.portfoliobackend.entity.Person;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectDto {

    @NotBlank
    private String name;
    @NotBlank
    private String techs;
    @NotBlank
    private String imgUrl;
    @NotBlank
    private int dateYear;
    @NotBlank
    private String link;
    @NotBlank
    private String description;
    private Person person;

    public ProjectDto() {
    }

    public ProjectDto(@NotBlank String name, @NotBlank String techs, @NotBlank String imgUrl,
            @NotBlank int dateYear, @NotBlank String link, @NotBlank String description, Person person) {
        this.name = name;
        this.techs = techs;
        this.imgUrl = imgUrl;
        this.dateYear = dateYear;
        this.link = link;
        this.description = description;
        this.person = person;
    }
    
}