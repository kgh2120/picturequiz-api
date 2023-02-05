package com.kk.picturequizapi.domain.comment.infra;

import com.kk.picturequizapi.domain.comment.command.domain.QuizIdValidateService;
import com.kk.picturequizapi.domain.comment.exception.QuizIdNotFoundException;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizIdValidateServiceImpl implements QuizIdValidateService {

    private final QuizSearchDao quizSearchDao;

    @Override
    public void validateQuizId(String quizId) {
        if(!quizSearchDao.isExistsQuizId(QuizId.of(quizId)))
            throw new QuizIdNotFoundException();

    }
}
