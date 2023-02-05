package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.users.event.NicknameChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class NicknameChangedHandler {

    private final QuizAuthorNicknameChangeService quizAuthorNicknameChangeService;

    @EventListener(NicknameChangedEvent.class)
    public void handle(NicknameChangedEvent event) {
        quizAuthorNicknameChangeService.changeAuthorNickname(event.getUserId(), event.getNickname());
    }

}
