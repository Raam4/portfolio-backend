package com.raama.portfoliobackend.dto;

import com.raama.portfoliobackend.entity.Person;
import java.sql.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExperienceDto {

    @NotBlank
    private String position;
    @NotBlank
    private String company;
    private String imgUrl;
    @NotBlank
    private String location;
    @NotBlank
    private Date dateStart;
    private Date dateEnd;
    @NotBlank
    private String description;
    private Person person;

    public ExperienceDto() {
    }

    public ExperienceDto(@NotBlank String position, @NotBlank String company, String imgUrl,
            @NotBlank String location, @NotBlank Date dateStart, Date dateEnd, @NotBlank String description, Person person) {
        this.position = position;
        this.company = company;
        this.imgUrl = imgUrl;
        this.location = location;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
        this.person = person;
    }
}