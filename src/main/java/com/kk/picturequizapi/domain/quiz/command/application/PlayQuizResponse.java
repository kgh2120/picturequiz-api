package com.kk.picturequizapi.domain.quiz.command.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PlayQuizResponse {
    private String url;
    private String characterName;

    public PlayQuizResponse(String ... quizInfo) {
        this.url = quizInfo[0];
        this.characterName = quizInfo[1];
    }
}
