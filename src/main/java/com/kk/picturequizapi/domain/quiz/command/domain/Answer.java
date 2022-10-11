package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable @NoArgsConstructor @Getter
public class Answer {

    @Embedded
    private CharacterId characterId;

    private String name;

    public Answer(CharacterId characterId, String name) {
        this.characterId = characterId;
        this.name = name;
    }
}
