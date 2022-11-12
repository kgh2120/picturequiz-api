package com.kk.picturequizapi.domain.tag.query.ui;

import com.kk.picturequizapi.domain.tag.query.application.TagSearchService;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@Validated
@RequiredArgsConstructor
@RestController
public class TagSearchController {

    private final TagSearchService tagSearchService;


    @GetMapping("/tag")
    public ResponseEntity<TagSearch> searchTagByName(
            @RequestParam("name") @Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣0-9A-Za-z]{1,5}") String name) {
        TagSearch tag = tagSearchService.findTagByName(name);
        return ResponseEntity.ok(tag);
    }
}
