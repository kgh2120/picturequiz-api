package com.kk.picturequizapi.domain.tag.query.application;

import com.kk.picturequizapi.domain.tag.exception.TagNotFoundException;
import com.kk.picturequizapi.domain.tag.query.dao.TagSearchDao;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagSearchService {
    private final TagSearchDao searchDao;

    public TagSearch findTagByName(String name) {
        return searchDao.findByName(name)
                .orElseThrow(TagNotFoundException::new);
    }
}
