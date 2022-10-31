package com.kk.picturequizapi.domain.tag.command.application;

import com.kk.picturequizapi.domain.tag.command.domain.Tag;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.tag.command.domain.TagRepository;
import com.kk.picturequizapi.domain.tag.exception.TagNameDuplicateException;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TagCreateServiceTest {

    @Mock
    TagRepository repository;

    @InjectMocks
    TagCreateService service;

    
    @Test
    void createTag_success () throws Exception{
        //given
        given(repository.nextId())
                .willReturn("123");
        given(repository.existsByName(any()))
                .willReturn(false);
        given(repository.save(any()))
                .willReturn(new Tag(createTagId(), "운동"));
        //when
        TagCreateRequest r = new TagCreateRequest();
        r.setName("운동");
        TagSearch tag = service.createTag(r);

        //then
        assertThat(tag.getName())
                .isEqualTo(r.getName());
        
    
    }

    @Test
    void createTag_exception_duplicate () throws Exception{

        given(repository.existsByName(any()))
                .willReturn(true);

        //when
        TagCreateRequest r = new TagCreateRequest();
        r.setName("운동");
        assertThatThrownBy(() -> service.createTag(r))
                .isInstanceOf(TagNameDuplicateException.class);

    }

    private TagId createTagId() throws Exception {
        Class<TagId> tagIdClass = TagId.class;
        Constructor<TagId> con = tagIdClass.getDeclaredConstructor(String.class);
        con.setAccessible(true);
        return con.newInstance("123");

    }
}