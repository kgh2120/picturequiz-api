package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import com.kk.picturequizapi.domain.users.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizAuthorNicknameChangeService {

    private final QuizRepository quizRepository;


    public void changeAuthorNickname(long userId, String nickname) {
        quizRepository.findAllByAuthor_UserId(UserId.of(userId))
                .forEach(q -> q.changeAuthorNickname(nickname));
    }
}
