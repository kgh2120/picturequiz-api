package com.kk.picturequizapi.domain.character.query.application;

import com.kk.picturequizapi.domain.character.exception.CharacterNotFoundException;
import com.kk.picturequizapi.domain.character.query.dao.CharacterSearchDao;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CharacterSearchService {

    private final CharacterSearchDao characterSearchDao;


    // 이름으로 캐릭터를 검색하여 상위 5개의 검색 결과를 불러온다.
    @Cacheable(key = "#name", cacheNames = "characters")
    public List<CharacterSearch> find5CharactersByName(String name) {
        return characterSearchDao.findTop5ByNameStartsWithOrderByName(name);
    }

    public CharacterSearch findCharacterById(Long id) {
        return characterSearchDao.findById(id)
                .orElseThrow(CharacterNotFoundException::new);
    }
}
