package com.kk.picturequizapi.domain.character.infra;

import com.kk.picturequizapi.domain.character.query.dao.CharacterSearchDao;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCharacterSearchDao extends CharacterSearchDao, JpaRepository<CharacterSearch, Long> {




    List<CharacterSearch> findTop5ByNameStartsWithOrderByName(@Param("name") String name);


}
