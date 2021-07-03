package com.bogdanbrl.config;

import com.bogdanbrl.converters.StringToContinentConverter;
import com.bogdanbrl.converters.StringToCountryConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToContinentConverter());
        registry.addConverter(new StringToCountryConverter());
    }
}
