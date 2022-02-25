package com.raama.portfoliobackend.dto;

import java.sql.Date;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducationDto {

    @NotBlank
    private String title;
    @NotBlank
    private String institution;
    @NotBlank
    private String degree;
    @NotBlank
    private String location;
    @NotBlank
    private Date dateStart;
    private Date dateEnd;
    @NotBlank
    private String description;

    public EducationDto() {
    }

    public EducationDto(@NotBlank String title, @NotBlank String institution, @NotBlank String degree,
            @NotBlank String location, @NotBlank Date dateStart, Date dateEnd, @NotBlank String description) {
        this.title = title;
        this.institution = institution;
        this.degree = degree;
        this.location = location;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.description = description;
    }
}
