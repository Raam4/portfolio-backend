package com.raama.portfoliobackend.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectDto {

    @NotBlank
    private String name;
    @NotBlank
    private String techs;
    @NotBlank
    private String picLoc;
    @NotBlank
    private int dateYear;
    @NotBlank
    private String link;
    @NotBlank
    private String description;

    public ProjectDto() {
    }

    public ProjectDto(@NotBlank String name, @NotBlank String techs, @NotBlank String picLoc,
            @NotBlank int dateYear, @NotBlank String link, @NotBlank String description) {
        this.name = name;
        this.techs = techs;
        this.picLoc = picLoc;
        this.dateYear = dateYear;
        this.link = link;
        this.description = description;
    }
    
}