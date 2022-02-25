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
    private String picLoc;
    @NotBlank
    private String location;
    private String about;

    public PersonDto() {
    }

    public PersonDto(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String title,
            String picLoc, @NotBlank String location, String about) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.picLoc = picLoc;
        this.location = location;
        this.about = about;
    }
    
}