package com.raama.portfoliobackend.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkillDto {

    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String icon;

    public SkillDto() {
    }

    public SkillDto(@NotBlank String name, @NotBlank String type, @NotBlank String icon) {
        this.name = name;
        this.type = type;
        this.icon = icon;
    }
}
