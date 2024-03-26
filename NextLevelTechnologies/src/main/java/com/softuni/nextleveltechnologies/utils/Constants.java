package com.softuni.nextleveltechnologies.utils;

import java.nio.file.Path;

public enum Constants {
    ;
    public static final Path COMPANIES_IMPORT_XML = Path.of("NextLevelTechnologies", "src", "main", "resources", "files", "xmls", "companies.xml");

    public static final String SUCCESSFULLY_IMPORTED_DATA_MESSAGE_FORMAT = "Successfully imported %s - %s%n";
    public static final String INCORRECT_DATA_MESSAGE_FORMAT = "Invalid %s%n";
}
