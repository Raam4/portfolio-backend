package com.raama.portfoliobackend.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String title;
    private String imgUrl;
    @NotBlank
    private String location;
    @NotBlank
    private String about;

    public PersonDto() {
    }

    public PersonDto(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String title,
            String imgUrl, @NotBlank String location, @NotBlank String about) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.imgUrl = imgUrl;
        this.location = location;
        this.about = about;
    }
    
}