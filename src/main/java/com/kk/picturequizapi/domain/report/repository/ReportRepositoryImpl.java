package com.kk.picturequizapi.domain.report.repository;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.users.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JpaReportRepository jpaReportRepository;
    @Override
    public boolean isExistReport(TargetId of, TargetType targetType, UserId userId,
            ReportType etc) {
        return jpaReportRepository.existsReportByTargetIdAndTargetTypeAndReporterUserIdAndAndReportContent_ReportType(of, targetType, userId, etc);
    }

    @Override
    public void save(Report report) {
        jpaReportRepository.save(report);
    }
}
