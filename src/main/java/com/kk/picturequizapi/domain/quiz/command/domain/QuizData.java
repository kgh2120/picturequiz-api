package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.global.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "Quiz")
@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizData extends BaseEntity {

    @EmbeddedId
    private QuizId quizId;
    private long viewCount;
    @Embedded
    private Author author;
    @Embedded
    private Picture picture;
    @Embedded
    private Answer answer;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "quiz_tag", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<QuizTag> quizTags;

    public QuizData(QuizId quizId, Author author, Picture picture, Answer answer, List<QuizTag> quizTags) {
        this.viewCount = 0;
        setQuizId(quizId);
        setAuthor(author);
        setAnswer(answer);
        setQuizTags(quizTags);
        this.picture = picture; // ????
    }

    private void setQuizId(QuizId quizId) {
        if (quizId == null) {

        }
        this.quizId = quizId;
    }

    private void setAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException();
        }
        this.author = author;
    }

    private void setAnswer(Answer answer) {
        if (answer == null) {

        }
        this.answer = answer;
    }
    private void setQuizTags(List<QuizTag> quizTags) {
        if (quizTags == null) {

        }
        this.quizTags = quizTags;
    }
    private void setPicture(Picture picture) {
        if (picture == null) {

        }
        this.picture = picture;
    }

    public String playQuiz() {
        return null;
    }


}
