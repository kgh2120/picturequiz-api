package com.kk.picturequizapi.domain.tag.command.domain;

import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tag")
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter
public class Tag {

    @EmbeddedId
    private TagId tagId;
    private String name;

    @Column(name = "tag_color")
    private String colorCode;

    public Tag(TagId tagId, String name, String color) {
        this.tagId = tagId;
        this.name = name;
        this.colorCode = color;
    }


    public TagSearch createDto() {
        return new TagSearch(tagId.getId(), name, colorCode);
    }
}
