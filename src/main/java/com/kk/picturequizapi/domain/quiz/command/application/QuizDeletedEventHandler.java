package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.comment.command.application.ClearCommentService;
import com.kk.picturequizapi.domain.report.application.ClearReportService;
import com.kk.picturequizapi.global.event.QuizDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class QuizDeletedEventHandler {

    private final ClearCommentService clearCommentService;
    private final ClearReportService clearReportService;

    @EventListener(QuizDeletedEvent.class)
    public void handle(QuizDeletedEvent event){
        clearCommentService.clearComment(event.getQuizId());
        clearReportService.clearQuizReport(event.getQuizId());
    }
}
