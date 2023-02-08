package com.kk.picturequizapi.domain.report.application;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ClearReportService {

    private final ReportRepository reportRepository;

    public void clearQuizReport(String quizId) {
        reportRepository.clearTarget(TargetId.of(quizId), TargetType.QUIZ);
    }
    public void clearCommentReports(List<String> ids) {
        reportRepository.clearCommentReports(ids);
    }
    public void clearCommentReport(String commentId) {
        reportRepository.clearTarget(TargetId.of(commentId), TargetType.COMMENT);
    }
}
