package com.raama.portfoliobackend.dto;

import javax.validation.constraints.NotBlank;

import com.raama.portfoliobackend.entity.Person;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkillDto {

    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String imgUrl;
    private Person person;

    public SkillDto() {
    }

    public SkillDto(@NotBlank String name, @NotBlank String type, @NotBlank String imgUrl, Person person) {
        this.name = name;
        this.type = type;
        this.imgUrl = imgUrl;
        this.person = person;
    }
}
