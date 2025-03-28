package br.edu.utfpr;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractBeanField<String, LocalDate> {

    @Override
    protected LocalDate convert(String value) {
        if (value != null && !value.isEmpty()) {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        return null;
    }
}
