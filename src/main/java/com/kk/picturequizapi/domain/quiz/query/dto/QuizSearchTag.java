package com.kk.picturequizapi.domain.quiz.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class QuizSearchTag {
    private String name;
    private String color;
}
