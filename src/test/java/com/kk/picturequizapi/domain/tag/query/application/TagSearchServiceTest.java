package com.kk.picturequizapi.domain.tag.query.application;

import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.domain.tag.query.dao.TagSearchDao;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Field;
import java.util.Optional;

import static com.kk.picturequizapi.domain.tag.TagTestUtil.createTag;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TagSearchServiceTest {

    @Mock
    TagSearchDao dao;

    @InjectMocks
    TagSearchService service;


    @Test
    void findTagByName () throws Exception{
        //given
        given(dao.findByName(any()))
                .willReturn(createTag());
        //when
        TagSearch tag = service.findTagByName("운동");
        //then
        assertThat(tag.getName())
                .isEqualTo("운동");
    }
    
    @Test
    void findTagByName_NotFound () throws Exception{
        //given
        given(dao.findByName(any()))
                .willReturn(Optional.empty());
        //when //then
        assertThatThrownBy(() -> service.findTagByName("운동"))
                .isInstanceOf(TagNotFoundException.class);
    }



}