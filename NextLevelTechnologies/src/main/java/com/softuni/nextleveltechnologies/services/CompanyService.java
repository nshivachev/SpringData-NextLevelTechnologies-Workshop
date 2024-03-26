package com.softuni.nextleveltechnologies.services;


import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CompanyService {
    String getXMLContent() throws IOException;

    String importCompanies() throws IOException, JAXBException;

    boolean areImported();
}
