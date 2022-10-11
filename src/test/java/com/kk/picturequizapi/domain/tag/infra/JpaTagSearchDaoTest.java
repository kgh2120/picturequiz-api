package com.kk.picturequizapi.domain.tag.infra;

import com.kk.picturequizapi.domain.tag.command.domain.TagData;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.domain.tag.query.dao.TagSearchDao;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaTagSearchDaoTest {


    @Autowired
    EntityManager em;
    @Autowired
    TagSearchDao dao;



    @Test
    void findByName () throws Exception{
        //given
        String name = "운동";
        TagData tagData = new TagData(TagId.of("123"), name);
        em.persist(tagData);
        //when
        Optional<TagSearch> tagSearch = dao.findByName(name);
        //then
        assertThat(tagSearch.get().getName())
                .isEqualTo(name);
    }
    @Test
    void findByName_NotFound () throws Exception{
        //given
        String name = "운동";
        TagData tagData = new TagData(TagId.of("123"), "공부");
        em.persist(tagData);
        //when
        Optional<TagSearch> tagSearch = dao.findByName(name);
        //then
        assertThatThrownBy(() -> tagSearch.orElseThrow(TagNotFoundException::new))
                .isInstanceOf(TagNotFoundException.class);
    }
}