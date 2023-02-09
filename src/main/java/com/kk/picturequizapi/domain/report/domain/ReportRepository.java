package com.kk.picturequizapi.domain.report.domain;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.List;
import java.util.UUID;

public interface ReportRepository {

    boolean isExistReport(TargetId of, TargetType targetType, UserId userId,ReportType etc);

    void clearTarget(TargetId targetId, TargetType targetType);

    default String nextId() {
        return UUID.randomUUID().toString();
    }

    void save(Report report);

    void clearCommentReports(List<String> ids);
}
