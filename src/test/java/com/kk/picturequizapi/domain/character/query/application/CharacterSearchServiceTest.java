package com.kk.picturequizapi.domain.character.query.application;

import com.kk.picturequizapi.domain.character.query.dao.CharacterSearchDao;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.kk.picturequizapi.domain.character.CharacterTestUtil.create5CharacterSearch;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CharacterSearchServiceTest {

    @Mock
    CharacterSearchDao dao;

    @InjectMocks
    CharacterSearchService service;
    
    @Test
    void find5CharactersByName () throws Exception{
        //given
        given(dao.findTop5ByNameStartsWithOrderByName(any()))
                .willReturn(create5CharacterSearch());
        //when
        List<CharacterSearch> characters = service.find5CharactersByName("김");

        //then
        Assertions.assertThat(characters.size()).isSameAs(5);
        
    
    }
    


}