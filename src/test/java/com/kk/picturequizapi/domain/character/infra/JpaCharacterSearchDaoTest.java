package com.kk.picturequizapi.domain.character.infra;

import com.kk.picturequizapi.domain.character.query.dao.CharacterSearchDao;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class JpaCharacterSearchDaoTest {

    @Autowired
    CharacterSearchDao dao;

    @Test
    void 이름으로_캐릭터_검색하기 () throws Exception{
        //given

        //when
        List<CharacterSearch> findedCharacters
                = dao.findTop5ByNameStartsWithOrderByName("김");
        //then
        assertThat(findedCharacters.size()).isSameAs(5);
    }
    
    @Test
    void findById () throws Exception{
        //given
        Long id = 1L;
        //when
        CharacterSearch cs = dao.findById(id).get();
        //then
        assertThat(cs.getCharacterId()).isSameAs(id);
        
    
    }

}