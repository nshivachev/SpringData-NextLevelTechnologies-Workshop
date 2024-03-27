package com.softuni.nextleveltechnologies.models.dtos.employees;

import com.softuni.nextleveltechnologies.models.dtos.projects.ProjectImportDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeImportDto {
    @NotNull
    @XmlElement(name = "first-name")
    private String firstName;

    @NotNull
    @XmlElement(name = "last-name")
    private String lastName;

    @NotNull
    @XmlElement
    private Integer age;

    @NotNull
    @XmlElement
    private ProjectImportDto project;
}
