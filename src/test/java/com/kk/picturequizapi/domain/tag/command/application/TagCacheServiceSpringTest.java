package com.kk.picturequizapi.domain.tag.command.application;

import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.domain.tag.query.application.TagSearchService;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class TagCacheServiceSpringTest {

    
    @Autowired TagCreateService createService;
    @Autowired
    TagSearchService searchService;
    
    @Test
    void tagIntegrateTest () throws Exception{
        //given
        String tagName = "개그맨";
        //when
        TagSearch created = null;
        try {
        TagSearch find = searchService.findTagByName(tagName);
//        Assertions.assertThat(find.getName()).isEqualTo(tagName);
        } catch (TagNotFoundException e) {
            System.out.println(e.getErrorMessage());
            TagCreateRequest request = new TagCreateRequest();
            request.setName(tagName);
            created = createService.createTag(request);
        }
        //then

        TagSearch finded = searchService.findTagByName(tagName);
//
        assertThat(finded.getName()).isEqualTo(tagName);
        assertThat(created.getName()).isEqualTo(finded.getName());
    }
}