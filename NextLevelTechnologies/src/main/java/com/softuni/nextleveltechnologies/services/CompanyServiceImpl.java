package com.softuni.nextleveltechnologies.services;

import com.softuni.nextleveltechnologies.models.dtos.companies.CompanyImportWrapperDto;
import com.softuni.nextleveltechnologies.models.entities.Company;
import com.softuni.nextleveltechnologies.repositories.CompanyRepository;
import com.softuni.nextleveltechnologies.utils.ValidationUtils;
import com.softuni.nextleveltechnologies.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.softuni.nextleveltechnologies.utils.Constants.*;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtils validationUtils) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
    }

    @Override
    public String getXMLContent() throws IOException {
        final List<String> lines = Files.readAllLines(COMPANIES_IMPORT_XML);

        return String.join("\n", lines);
    }

    @Override
    public String importCompanies() throws IOException, JAXBException {
//        final String xmlContent = this.getXMLContent();
//        final ByteArrayInputStream stream = new ByteArrayInputStream(xmlContent.getBytes());
//
//        final JAXBContext jaxbContext = JAXBContext.newInstance(CompanyImportWrapperDto.class);
//
//        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        final CompanyImportWrapperDto companiesDto = (CompanyImportWrapperDto) unmarshaller.unmarshal(stream);

//        final ValidationUtils validationUtils = new ValidationUtils();

        final StringBuilder stringBuilder = new StringBuilder();

        List<Company> companies = xmlParser.fromFile(COMPANIES_IMPORT_XML, CompanyImportWrapperDto.class).getCompanies()
                .stream()
                .filter(companyDto -> {
                    final boolean isValid = validationUtils.isValid(companyDto)
                            && companyRepository.findByName(companyDto.getName()).isEmpty();

                    if (!isValid) {
                        stringBuilder.append(String.format(INCORRECT_DATA_MESSAGE_FORMAT, "company"));
                        return isValid;
                    }

                    stringBuilder.append(String.format(SUCCESSFULLY_IMPORTED_DATA_MESSAGE_FORMAT, "company", companyDto.getName()));

                    return isValid;
                })
                .map(companyDto -> modelMapper.map(companyDto, Company.class))
                .toList();

        companyRepository.saveAllAndFlush(companies);

        return stringBuilder.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }
}
