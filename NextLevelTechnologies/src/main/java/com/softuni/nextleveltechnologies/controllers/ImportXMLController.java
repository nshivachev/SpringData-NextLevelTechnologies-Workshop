package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.services.CompanyService;
import com.softuni.nextleveltechnologies.services.EmployeeService;
import com.softuni.nextleveltechnologies.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ImportXMLController {
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportXMLController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {
        boolean[] importStatus = {
                companyService.areImported(),
                projectService.areImported(),
                employeeService.areImported()
        };

        model.addAttribute("areImported", importStatus);

        return "xml/import-xml";
    }

    @GetMapping("/import/companies")
    public String viewImportCompanies(Model model) throws IOException {
        String companiesXML = this.companyService.getXMLContent();

        model.addAttribute("companies", companiesXML);

        return "xml/import-companies";
    }

    @PostMapping("/import/companies")
    public String importCompanies() throws IOException, JAXBException {
        System.out.println(this.companyService.importCompanies());

        return "redirect:/import/xml";
    }

    @GetMapping("/import/projects")
    public String viewImportProjects(Model model) throws IOException {
        String projectsXML = this.projectService.getXMLContent();

        model.addAttribute("projects", projectsXML);

        return "xml/import-projects";
    }

    @PostMapping("/import/projects")
    public String importProjects() throws IOException, JAXBException {
        System.out.println(this.projectService.importProjects());

        return "redirect:/import/xml";
    }

    @GetMapping("/import/employees")
    public String viewImportEmployees(Model model) throws IOException {
        String employeesXML = this.employeeService.getXMLContent();

        model.addAttribute("employees", employeesXML);

        return "xml/import-employees";
    }

    @PostMapping("/import/employees")
    public String importEmployees() throws IOException, JAXBException {
        System.out.println(this.employeeService.importEmployees());

        return "redirect:/import/xml";
    }
}
