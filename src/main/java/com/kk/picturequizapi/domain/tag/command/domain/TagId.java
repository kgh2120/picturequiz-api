package com.kk.picturequizapi.domain.tag.command.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @NoArgsConstructor
public class TagId implements Serializable {

    @Column(name = "tag_id")
    private String id;


    public TagId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagId tagId = (TagId) o;
        return Objects.equals(id, tagId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
