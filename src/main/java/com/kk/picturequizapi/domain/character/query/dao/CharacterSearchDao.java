package com.kk.picturequizapi.domain.character.query.dao;

import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;

import java.util.List;
import java.util.Optional;

public interface CharacterSearchDao {

    List<CharacterSearch> findTop5ByNameStartsWithOrderByName(String name);

    Optional<CharacterSearch> findById(Long id);
}
