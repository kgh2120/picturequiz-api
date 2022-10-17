package com.kk.picturequizapi.global.converter;

import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchOrderCondition;
import org.springframework.core.convert.converter.Converter;

public class QuizSearchOrderConditionConverter implements Converter<String, QuizSearchOrderCondition> {

    @Override
    public QuizSearchOrderCondition convert(String source) {
        return QuizSearchOrderCondition.valueOf(source.toUpperCase());
    }

}
