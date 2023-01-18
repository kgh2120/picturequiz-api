package com.kk.picturequizapi.domain.report;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.HashMap;
import java.util.Map;

public class FakeReportRepository implements
        ReportRepository {

    private Map<ReportId, Report> storage = new HashMap<>();


    @Override
    public boolean isExistReport(QuizId of, UserId userId, ReportType etc) {
        for (Report report : storage.values()) {
            if(report.getQuizId().equals(of)
                    && report.getReporterId().equals(userId)
                    && report.getReportType().equals(etc))
                return true;
        }
        return false;
    }

    @Override
    public void save(Report report) {
        storage.put(ReportId.of(report.getReportIdToString()),report);

    }
}