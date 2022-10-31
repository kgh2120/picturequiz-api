package com.kk.picturequizapi.domain.quiz.query.dto;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuizSearch {


    private String quizId;
    private String pictureUrl;
    private String characterName;
    private String authorNickname;

    private List<String> tagNames;

    public QuizSearch(QuizData quiz) {
        this.quizId = quiz.getQuizId().getId();
        this.pictureUrl = quiz.getPicture().getUrl();
        this.characterName = quiz.getAnswer().getName();
        this.authorNickname = quiz.getAuthor().getNickname();
        this.tagNames = new ArrayList<>();
        quiz.getQuizTags().forEach(qt ->
            tagNames.add(qt.getName())
        );
    }
}
