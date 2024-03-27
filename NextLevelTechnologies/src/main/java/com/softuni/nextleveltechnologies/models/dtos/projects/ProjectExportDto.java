package com.softuni.nextleveltechnologies.models.dtos.projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProjectExportDto {
    private String name;
    private String description;
    private BigDecimal payment;

    @Override
    public String toString() {
        return "Project Name: " + name + System.lineSeparator() +
                "   Description: " + description + System.lineSeparator() +
                "   " + payment;
    }
}
