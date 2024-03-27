package com.softuni.nextleveltechnologies.services;

import com.softuni.nextleveltechnologies.models.dtos.employees.EmployeeExportDto;
import com.softuni.nextleveltechnologies.models.dtos.employees.EmployeeImportWrapperDto;
import com.softuni.nextleveltechnologies.models.entities.Company;
import com.softuni.nextleveltechnologies.models.entities.Employee;
import com.softuni.nextleveltechnologies.models.entities.Project;
import com.softuni.nextleveltechnologies.repositories.CompanyRepository;
import com.softuni.nextleveltechnologies.repositories.EmployeeRepository;
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
import java.util.stream.Collectors;

import static com.softuni.nextleveltechnologies.utils.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, CompanyRepository companyRepository, XmlParser xmlParser, ValidationUtils validationUtils, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

    @Override
    public String getXMLContent() throws IOException {
        final List<String> lines = Files.readAllLines(EMPLOYEES_IMPORT_XML);

        return String.join("\n", lines);
    }

    @Override
    public String importEmployees() throws JAXBException, FileNotFoundException {
        final StringBuilder stringBuilder = new StringBuilder();

        xmlParser.fromFile(EMPLOYEES_IMPORT_XML, EmployeeImportWrapperDto.class).getEmployees()
                .forEach(employeeDto -> {
                    final boolean isValid = validationUtils.isValid(employeeDto)
                            && employeeRepository.findByFirstNameAndLastName(employeeDto.getFirstName(), employeeDto.getLastName()).isEmpty();

                    if (!isValid) {
                        stringBuilder.append(String.format(INCORRECT_DATA_MESSAGE_FORMAT, "employee"));
                        return;
                    }

                    final Project project = projectRepository.findByName(employeeDto.getProject().getName()).orElseThrow(IllegalArgumentException::new);
                    final Company company = companyRepository.findByName(employeeDto.getProject().getCompany().getName()).orElseThrow(IllegalArgumentException::new);
                    final Employee employee = modelMapper.map(employeeDto, Employee.class);

                    project.setCompany(company);
                    employee.setProject(project);

                    employeeRepository.saveAndFlush(employee);

                    stringBuilder.append(String.format(SUCCESSFULLY_IMPORTED_DATA_MESSAGE_FORMAT,
                            "employee", employeeDto.getFirstName() + " " + employeeDto.getLastName()));
                });

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportEmployees(int age) {
        return employeeRepository.findAllByAgeAfter(age)
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeExportDto.class))
                .map(EmployeeExportDto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }
}
