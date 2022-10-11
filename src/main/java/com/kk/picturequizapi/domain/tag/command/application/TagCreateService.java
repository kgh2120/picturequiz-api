package com.kk.picturequizapi.domain.tag.command.application;


import com.kk.picturequizapi.domain.tag.command.domain.TagData;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.tag.command.domain.TagRepository;
import com.kk.picturequizapi.domain.tag.exception.TagNameDuplicateException;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class TagCreateService {

    private final TagRepository tagRepository;

    public TagSearch createTag(TagCreateRequest tagRequest) {

        if(tagRepository.existsByName(tagRequest.getName())) {
            throw new TagNameDuplicateException();
        }

        TagData tagData = new TagData(TagId.of(tagRepository.nextId()), tagRequest.getName());
        tagRepository.save(tagData);
        return tagData.createDto();
    }
}
