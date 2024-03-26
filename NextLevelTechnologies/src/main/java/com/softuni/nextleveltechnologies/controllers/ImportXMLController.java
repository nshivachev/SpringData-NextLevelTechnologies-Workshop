package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.services.CompanyService;
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

    @Autowired
    public ImportXMLController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/import/xml")
    public String index(Model model) {
        boolean[] importStatus = {companyService.areImported(), false, false};

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
}
