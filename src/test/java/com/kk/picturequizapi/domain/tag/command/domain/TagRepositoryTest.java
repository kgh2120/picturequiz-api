package com.kk.picturequizapi.domain.tag.command.domain;

import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TagRepositoryTest {

    @Autowired TagRepository repository;
    @Autowired
    EntityManager em;
    
    @Test
    void save () throws Exception{
        //given
        TagData data = new TagData(TagId.of(repository.nextId()), "운동");

        //when
        repository.save(data);
        
        //then
        TagData search = em.find(TagData.class, data.getTagId());

        assertThat(search.getName())
                .isEqualTo(data.getName());

    }
    
    @Test
    void nextId () throws Exception{
        //given
        String id = repository.nextId();
        //when
        String nextId = repository.nextId();
        //then
        assertThat(id).isNotEqualTo(nextId);
        
    
    }

    @Test
    void isExist_true () throws Exception{
        //given
        TagData data = new TagData(TagId.of(repository.nextId()), "운동");

        //when
        repository.save(data);

        //then

        boolean result = repository.existsByName("운동");

        assertThat(result).isTrue();


    }

    @Test
    void isExist_false () throws Exception{
        //given
        TagData data = new TagData(TagId.of(repository.nextId()), "운동");

        //when
        repository.save(data);

        //then

        boolean result = repository.existsByName("공부");

        assertThat(result).isFalse();


    }
    
}