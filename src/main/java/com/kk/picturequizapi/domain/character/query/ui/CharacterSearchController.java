package com.kk.picturequizapi.domain.character.query.ui;

import com.kk.picturequizapi.domain.character.query.application.CharacterSearchService;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CharacterSearchController {

    private final CharacterSearchService characterSearchService;

    // 200 -> ok 검색 결과가 없다고 클라이언트 오류인가?? 없으면 없다고 해도 될듯..?
    @GetMapping("/character")
    public ResponseEntity<List<CharacterSearch>> findCharactersByName(@RequestParam("name")String name) {
        List<CharacterSearch> characters = characterSearchService.find5CharactersByName(name);
        return ResponseEntity.ok(characters);
    }
}
