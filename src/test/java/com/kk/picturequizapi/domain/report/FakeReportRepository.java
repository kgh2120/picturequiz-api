package com.kk.picturequizapi.domain.report;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeReportRepository implements
        ReportRepository {

    private Map<ReportId, Report> storage = new HashMap<>();


    @Override
    public boolean isExistReport(TargetId of, TargetType targetType, UserId userId, ReportType etc) {
        for (Report report : storage.values()) {
            if(report.getTargetId().equals(of)
                    && report.getTargetType().equals(targetType)
                    && report.getReporterId().equals(userId)
                    && report.getReportType().equals(etc))
                return true;
        }
        return false;
    }

    @Override
    public void clearTarget(TargetId targetId, TargetType targetType) {

    }


    @Override
    public void save(Report report) {
        storage.put(ReportId.of(report.getReportIdToString()),report);

    }

    @Override
    public void clearCommentReports(List<String> ids) {

    }
}
