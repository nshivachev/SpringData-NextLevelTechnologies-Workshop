package com.softuni.nextleveltechnologies.models.dtos.projects;

import com.softuni.nextleveltechnologies.models.dtos.companies.CompanyImportDto;
import com.softuni.nextleveltechnologies.utils.LocalDateAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
@NotNull
public class ProjectImportDto {
    @XmlElement
    private String name;

    @NotNull
    @XmlElement
    private String description;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "start-date")
    private LocalDate startDate;

    @XmlElement(name = "is-finished")
    boolean isFinished;

    @NotNull
    @XmlElement
    private BigDecimal payment;

    @NotNull
    @XmlElement
    private CompanyImportDto company;
}
