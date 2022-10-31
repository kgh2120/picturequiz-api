package com.kk.picturequizapi.domain.tag.command.domain;

import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tag")
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter
public class Tag {

    @EmbeddedId
    private TagId tagId;
    private String name;

    public Tag(TagId tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }


    public TagSearch createDto() {
        return new TagSearch(tagId.getId(), name);
    }
}
