package com.bogdanbrl.converters;

import com.bogdanbrl.entity.CountryModel;
import org.springframework.core.convert.converter.Converter;

public class StringToCountryConverter implements Converter<String, CountryModel> {

    @Override
    public CountryModel convert(String name) {
        return new CountryModel(name);
    }
}
