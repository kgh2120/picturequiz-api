package com.kk.picturequizapi.domain.comment.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizDeletedEventHandler {

    private final ClearCommentService clearCommentService;

    @EventListener(QuizDeletedEvent.class)
    public void handle(QuizDeletedEvent event){
        clearCommentService.clearComment(event.getQuizId());

    }
}
