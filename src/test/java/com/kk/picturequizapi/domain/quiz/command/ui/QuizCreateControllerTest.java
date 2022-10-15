package com.kk.picturequizapi.domain.quiz.command.ui;

import com.kk.picturequizapi.global.config.SecurityConfig;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = {QuizCreateController.class},
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class QuizCreateControllerTest {




}