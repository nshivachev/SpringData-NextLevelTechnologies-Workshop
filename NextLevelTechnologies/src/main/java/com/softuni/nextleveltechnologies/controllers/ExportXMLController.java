package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.services.EmployeeService;
import com.softuni.nextleveltechnologies.services.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExportXMLController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ExportXMLController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/export/project-if-finished")
    public String exportProjects(Model model) {
        model.addAttribute("projectsIfFinished", projectService.exportProjects(true));

        return "export/export-project-if-finished.html";
    }

    @GetMapping("/export/employees-above")
    public String exportEmployees(Model model) {
        model.addAttribute("employeesAbove", employeeService.exportEmployees(25));

        return "export/export-employees-with-age.html";
    }
}
