package com.kk.picturequizapi.domain.tag.query.ui;

import com.kk.picturequizapi.domain.tag.query.application.TagSearchService;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagSearchController {

    private final TagSearchService tagSearchService;

    @GetMapping("/tag")
    public ResponseEntity<TagSearch> searchTagByName(@RequestParam("name")String name) {
        TagSearch tag = tagSearchService.findTagByName(name);
        return ResponseEntity.ok(tag);
    }
}
