package com.kk.picturequizapi.domain.tag.query.dao;

import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;

import java.util.Optional;

public interface TagSearchDao {

    Optional<TagSearch> findByName(String name);
}
