package com.kk.picturequizapi.domain.report.application;

import com.kk.picturequizapi.global.event.QuizClearedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class QuizClearedEventHandler {

    private final ClearReportService clearReportService;

    @EventListener
    public void handleQuizClearedEvent(QuizClearedEvent event) {

        log.info("quizClear!!! ={} ", event.getIds());
        clearReportService.clearCommentReports(event.getIds());
    }

}
