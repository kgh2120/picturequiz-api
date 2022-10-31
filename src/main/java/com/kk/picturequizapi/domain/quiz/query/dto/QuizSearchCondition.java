package com.kk.picturequizapi.domain.quiz.query.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class QuizSearchCondition {

    private String answerName;
    private QuizSearchOrderCondition orderCondition;
    private List<String> tagNames = new ArrayList<>();

    private int pageNum;



}
