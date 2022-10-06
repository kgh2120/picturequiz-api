package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable @NoArgsConstructor
public class QuizTag {

    @Embedded
    private TagId tagId;

    @Column(name = "tag_name")
    private String name;

    public QuizTag(TagId tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }
}
