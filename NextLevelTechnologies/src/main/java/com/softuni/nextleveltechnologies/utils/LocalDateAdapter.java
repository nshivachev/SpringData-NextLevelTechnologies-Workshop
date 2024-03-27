package com.softuni.nextleveltechnologies.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String marshal(LocalDate localDate) {
        return localDate.format(dateFormat);
    }

    @Override
    public LocalDate unmarshal(String localDate) {
        return LocalDate.parse(localDate, dateFormat);
    }
}
