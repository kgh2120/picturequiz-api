package com.kk.picturequizapi.domain.quiz.command.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable @NoArgsConstructor @Getter
public class Answer {
    private String name;

    public Answer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Answer{" +
                ", name='" + name + '\'' +
                '}';
    }
}
