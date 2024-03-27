package com.softuni.nextleveltechnologies.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmployeeService {
    String getXMLContent() throws IOException;

    String importEmployees() throws JAXBException, FileNotFoundException;

    boolean areImported();
}
