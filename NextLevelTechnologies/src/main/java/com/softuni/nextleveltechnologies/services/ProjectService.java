package com.softuni.nextleveltechnologies.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProjectService {
    String getXMLContent() throws IOException;

    String importProjects() throws JAXBException, FileNotFoundException;

    boolean areImported();
}
