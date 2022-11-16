package com.kk.picturequizapi.domain.tag.command.ui;

import com.kk.picturequizapi.domain.tag.command.application.TagCreateRequest;
import com.kk.picturequizapi.domain.tag.command.application.TagCreateService;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagCreateController {

    private final TagCreateService tagCreateService;

    @PostMapping("/tag")
    public ResponseEntity<TagSearch> createNewTag(@RequestBody @Validated TagCreateRequest body) {
        TagSearch tag = tagCreateService.createTag(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);

    }


}
