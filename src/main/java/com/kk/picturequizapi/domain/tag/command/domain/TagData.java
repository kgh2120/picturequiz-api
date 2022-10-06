package com.kk.picturequizapi.domain.tag.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tag")
@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED) @Getter
public class TagData {

    @EmbeddedId
    private TagId tagId;
    private String name;

    public TagData(TagId tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }
}
