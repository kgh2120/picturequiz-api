package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable @NoArgsConstructor @Getter
public class QuizTag {

    @Embedded
    private TagId tagId;

    @Column(name = "tag_name")
    private String name;

    @Column(name = "tag_color")
    private String color;

    public QuizTag(TagId tagId, String name, String color) {
        this.tagId = tagId;
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "QuizTag{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
