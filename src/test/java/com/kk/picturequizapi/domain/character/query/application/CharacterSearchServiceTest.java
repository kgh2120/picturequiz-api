package com.kk.picturequizapi.domain.character.query.application;

import com.kk.picturequizapi.domain.character.query.dao.CharacterSearchDao;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.kk.picturequizapi.TestFactory.create5CharacterSearch;
import static com.kk.picturequizapi.TestFactory.createCharacterSearch;
import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(characters.size()).isSameAs(5);
    }
    @Test
    void findById_success () throws Exception{
        //given
        given(dao.findById(any()))
                .willReturn(Optional.of(createCharacterSearch()));
        
        //when
        CharacterSearch characterSearch = service.findCharacterById(1L);
        //then
        assertThat(characterSearch.getName()).isEqualTo("이름");
        
    
    }
    


}