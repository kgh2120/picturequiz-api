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

    private Long viewCount;

    @Embedded
    private Author author;

    @Embedded
    private Picture picture;

    @Embedded
    private Answer answer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "quiz_tag", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<QuizTag> quizTags;



    public String uploadPicture() {
        return null;
    }

    public String playQuiz() {
        return null;
    }


}
