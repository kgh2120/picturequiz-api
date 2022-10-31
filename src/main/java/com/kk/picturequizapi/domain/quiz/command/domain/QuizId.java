package com.kk.picturequizapi.domain.quiz.command.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable @NoArgsConstructor @Getter
public class QuizId implements Serializable {

    @Column(name = "quiz_id")
    private String id;

    private QuizId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizId quizId = (QuizId) o;
        return Objects.equals(id, quizId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static QuizId of(String id) {
        return new QuizId(id);
    }
}
