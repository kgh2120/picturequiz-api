package com.kk.picturequizapi.domain.character.query.ui;

import com.kk.picturequizapi.domain.character.query.application.CharacterSearchService;
import com.kk.picturequizapi.global.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.kk.picturequizapi.TestFactory.create5CharacterSearch;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CharacterSearchController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class CharacterSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CharacterSearchService service;

    @InjectMocks
    CharacterSearchController controller;
    
    
    @Test
    void characterSearchTest () throws Exception{
        //given
        given(service.find5CharactersByName(any()))
                .willReturn(create5CharacterSearch());
        
        //when
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/character?name=Kim")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("이름1"))
                .andDo(print());
        //then
        
    
    }

}