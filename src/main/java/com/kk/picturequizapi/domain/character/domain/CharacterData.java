package com.kk.picturequizapi.domain.character.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterData {

    @EmbeddedId
    private CharacterId characterId;
    private String name;
    private String job;

    public CharacterData(CharacterId characterId, String name, String job) {
        this.characterId = characterId;
        this.name = name;
        this.job = job;
    }


}
