package com.kk.picturequizapi.domain.quiz.query.dto;

import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class QuizSearch implements Serializable {


    private String quizId;
    private String pictureUrl;
    private String characterName;
    private String authorNickname;

    private List<QuizSearchTag> tags;

    public QuizSearch(Quiz quiz) {
        this.quizId = quiz.getQuizId().getId();
        this.pictureUrl = quiz.getPicture().getUrl();
        this.characterName = quiz.getAnswer().getName();
        this.authorNickname = quiz.getAuthor().getNickname();
        this.tags = new ArrayList<>();
        quiz.getQuizTags().forEach(qt ->
                tags.add(new QuizSearchTag(qt.getName(),qt.getColor()))
        );
    }
}
