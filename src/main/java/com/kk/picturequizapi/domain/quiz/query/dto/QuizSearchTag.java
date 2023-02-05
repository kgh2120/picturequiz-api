package com.kk.picturequizapi.domain.quiz.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class QuizSearchTag implements Serializable {
    private String name;
    private String color;
}
