package com.kk.picturequizapi.domain.tag.command.application;


import com.kk.picturequizapi.domain.tag.command.domain.Tag;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.tag.command.domain.TagRepository;
import com.kk.picturequizapi.domain.tag.exception.TagNameDuplicateException;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class TagCreateService {

    private final TagRepository tagRepository;

    @CachePut(key = "#tagRequest.getName()", cacheNames = "tagSearch")
    public TagSearch createTag(TagCreateRequest tagRequest) {

        if(tagRepository.existsByName(tagRequest.getName())) {
            throw new TagNameDuplicateException();
        }

        Tag tag = new Tag(TagId.of(tagRepository.nextId()), tagRequest.getName(), tagRequest.getColor());
        tagRepository.save(tag);
        return tag.createDto();
    }
}
