package com.softuni.nextleveltechnologies.models.dtos.employees;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeExportDto {
    private String firstName;
    private String lastName;
    private int age;
    private String projectName;

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + System.lineSeparator() +
                "   Age: " + age + System.lineSeparator() +
                "   Project name: " + projectName;
    }
}
