package com.kk.picturequizapi.domain.report.application;

import com.kk.picturequizapi.global.event.CommentDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentDeletedEventHandler {

    private final ClearReportService clearReportService;


    @EventListener
    public void handleCommentDeletedEvent(CommentDeletedEvent event){
        clearReportService.clearCommentReport(event.getCommentId());;

    }

}
