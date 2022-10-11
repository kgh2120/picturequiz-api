package com.kk.picturequizapi.domain.character.query.dao;

import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;

import java.util.List;

public interface CharacterSearchDao {

    List<CharacterSearch> findTop5ByNameStartsWithOrderByName(String name);
}
