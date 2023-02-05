package com.kk.picturequizapi.domain.quiz.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class QuizSearchResponse implements Serializable {

    private List<QuizSearch> quizzes = new ArrayList<>();
    private int nextPageNum;
    private boolean hasNext;
}
