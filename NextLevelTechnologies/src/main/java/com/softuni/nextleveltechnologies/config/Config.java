package com.softuni.nextleveltechnologies.config;

import com.softuni.nextleveltechnologies.utils.ValidationUtils;
import com.softuni.nextleveltechnologies.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtils createValidationUtils() {
        return new ValidationUtils();
    }

    @Bean
    public XmlParser createXmlParser() {
        return new XmlParser();
    }
}
