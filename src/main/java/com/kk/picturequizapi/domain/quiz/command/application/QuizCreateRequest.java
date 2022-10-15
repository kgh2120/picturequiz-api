package com.kk.picturequizapi.domain.quiz.command.application;

import lombok.Data;

import java.util.List;

@Data
public class QuizCreateRequest {

    private Long characterId;
    private List<String> tagNames;


}
