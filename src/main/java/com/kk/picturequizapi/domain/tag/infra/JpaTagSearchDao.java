package com.kk.picturequizapi.domain.tag.infra;

import com.kk.picturequizapi.domain.tag.query.dao.TagSearchDao;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTagSearchDao extends TagSearchDao, JpaRepository<TagSearch, String> {

    Optional<TagSearch> findByName(String name);
}
