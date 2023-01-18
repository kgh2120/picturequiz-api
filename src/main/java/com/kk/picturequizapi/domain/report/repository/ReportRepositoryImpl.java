package com.kk.picturequizapi.domain.report.repository;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.users.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JpaReportRepository jpaReportRepository;

    @Override
    public boolean isExistReport(QuizId of, UserId userId, ReportType etc) {
        return jpaReportRepository.existsReportByQuizIdAndReporterUserIdAndAndReportContent_ReportType(of, userId, etc);
    }

    @Override
    public void save(Report report) {
        jpaReportRepository.save(report);
    }
}
