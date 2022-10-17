package com.kk.picturequizapi.global.config;

import com.kk.picturequizapi.global.converter.QuizSearchOrderConditionConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new QuizSearchOrderConditionConverter());
    }
}
