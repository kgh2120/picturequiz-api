package com.kk.picturequizapi.domain.character.command.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public class CharacterId implements Serializable {

    @Column(name = "character_id")
    private Long id;

    private CharacterId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterId that = (CharacterId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static CharacterId of(Long id) {
        return new CharacterId(id);
    }


}
