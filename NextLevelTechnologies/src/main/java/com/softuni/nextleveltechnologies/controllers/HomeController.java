package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.services.CompanyService;
import com.softuni.nextleveltechnologies.services.EmployeeService;
import com.softuni.nextleveltechnologies.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public HomeController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("home")
    public String home(Model model) {
        boolean importStatus = companyService.areImported()
                && projectService.areImported()
                && employeeService.areImported();

        model.addAttribute("areImported", importStatus);

        return "home";
    }
}
