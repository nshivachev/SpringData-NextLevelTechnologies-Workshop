package com.softuni.nextleveltechnologies.services;

import com.softuni.nextleveltechnologies.models.dtos.projects.ProjectImportWrapperDto;
import com.softuni.nextleveltechnologies.models.entities.Company;
import com.softuni.nextleveltechnologies.models.entities.Project;
import com.softuni.nextleveltechnologies.repositories.CompanyRepository;
import com.softuni.nextleveltechnologies.repositories.ProjectRepository;
import com.softuni.nextleveltechnologies.utils.ValidationUtils;
import com.softuni.nextleveltechnologies.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.softuni.nextleveltechnologies.utils.Constants.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, CompanyRepository companyRepository, XmlParser xmlParser, ValidationUtils validationUtils, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public String getXMLContent() throws IOException {
        final List<String> lines = Files.readAllLines(PROJECTS_IMPORT_XML);

        return String.join("\n", lines);
    }

    @Override
    public String importProjects() throws JAXBException, FileNotFoundException {
        final StringBuilder stringBuilder = new StringBuilder();

        xmlParser.fromFile(PROJECTS_IMPORT_XML, ProjectImportWrapperDto.class).getProjects()
                .forEach(projectDto -> {
                    final boolean isValid = validationUtils.isValid(projectDto)
                            && projectRepository.findByName(projectDto.getName()).isEmpty();

                    if (!isValid) {
                        stringBuilder.append(String.format(INCORRECT_DATA_MESSAGE_FORMAT, "project"));
                        return;
                    }

                    final Company company = companyRepository.findByName(projectDto.getCompany().getName()).orElseThrow(IllegalArgumentException::new);

                    final Project project = modelMapper.map(projectDto, Project.class);
                    project.setCompany(company);

                    projectRepository.saveAndFlush(project);

                    stringBuilder.append(String.format(SUCCESSFULLY_IMPORTED_DATA_MESSAGE_FORMAT, "project", projectDto.getName()));
                });

        return stringBuilder.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }
}
