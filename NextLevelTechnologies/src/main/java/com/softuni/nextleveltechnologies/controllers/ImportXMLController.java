package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.services.CompanyService;
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

    @Autowired
    public ImportXMLController(CompanyService companyService, ProjectService projectService) {
        this.companyService = companyService;
        this.projectService = projectService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {
        boolean[] importStatus = {companyService.areImported(), projectService.areImported(), false};

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
}
