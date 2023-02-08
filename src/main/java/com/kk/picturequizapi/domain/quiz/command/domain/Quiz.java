package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.quiz.exception.*;
import com.kk.picturequizapi.global.jpa.BaseEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Table(name = "Quiz")
@Entity @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz extends BaseEntity {

    @EmbeddedId
    private QuizId quizId;
    private long viewCount;
    @Embedded
    private Author author;
    @Embedded
    private Picture picture;
    @Embedded
    private Answer answer;

    private LocalDateTime createdDateTime;

    @BatchSize(size = 5)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "quiz_tag",  joinColumns = @JoinColumn(name = "quiz_id"))
    private List<QuizTag> quizTags;

    public Quiz(QuizId quizId, Author author, Picture picture, Answer answer, List<QuizTag> quizTags) {
        this.viewCount = 0;
        setQuizId(quizId);
        setAuthor(author);
        setAnswer(answer);
        setQuizTags(quizTags);
        setPicture(picture);
        createdDateTime = LocalDateTime.now();

    }

    private void setQuizId(QuizId quizId) {
        if (quizId == null) {
            throw new InputNullDataOnQuizIdException();
        }
        this.quizId = quizId;
    }

    private void setAuthor(Author author) {
        if (author == null) {
            throw new InputNullDataOnAuthorException();
        }
        this.author = author;
    }

    private void setAnswer(Answer answer) {
        if (answer == null) {
            throw new InputNullDataOnAnswerException();
        }
        this.answer = answer;
    }
    private void setQuizTags(List<QuizTag> quizTags) {
        if (quizTags == null) {
            throw new InputNullDataOnQuizTagsException();
        }
        this.quizTags = quizTags;
    }
    private void setPicture(Picture picture) {
        if (picture == null) {
            throw new InputNullDataOnPictureException();
        }
        this.picture = picture;
    }

    public String[] playQuiz() {
        this.viewCount++;
        return new String[]{this.picture.getUrl(), this.answer.getName()};
    }


    @Override
    public String toString() {
        return "QuizData{" +
                "quizId=" + quizId +
                ", viewCount=" + viewCount +
                ", author=" + author +
                ", picture=" + picture +
                ", answer=" + answer +
                ", quizTags=" + quizTags +
                '}';
    }

    public void changeAuthorNickname(String nickname) {
        this.author = author.changeNickname(nickname);
    }


}
