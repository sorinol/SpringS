package com.marsor.converters;

import com.marsor.entity.CityModel;
import org.springframework.core.convert.converter.Converter;

public class StringToCityConverter implements Converter<String, CityModel> {

    @Override
    public CityModel convert(String name) {
        return new CityModel(name);
    }
}
