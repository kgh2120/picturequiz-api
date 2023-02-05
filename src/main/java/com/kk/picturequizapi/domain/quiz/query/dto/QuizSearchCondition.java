package com.kk.picturequizapi.domain.quiz.query.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor
public class QuizSearchCondition implements Serializable {

    private String answerName;
    private QuizSearchOrderCondition orderCondition;
    private List<String> tagNames = new ArrayList<>();

    @NotNull
    @Min(0)
    private int pageNum;



}
