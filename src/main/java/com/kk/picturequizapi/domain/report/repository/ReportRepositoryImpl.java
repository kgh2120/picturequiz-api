package com.kk.picturequizapi.domain.report.repository;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.report.infra.QueryDslReportDao;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JpaReportRepository jpaReportRepository;
    private final QueryDslReportDao queryDslReportDao;
    @Override
    public boolean isExistReport(TargetId of, TargetType targetType, UserId userId,
            ReportType etc) {
        return jpaReportRepository.existsReportByTargetIdAndTargetTypeAndReporterUserIdAndAndReportContent_ReportType(of, targetType, userId, etc);
    }

    @Override
    public void clearTarget(TargetId targetId, TargetType targetType) {
        jpaReportRepository.deleteAllByTargetIdAndTargetType(targetId,targetType);

    }

    @Override
    public void save(Report report) {
        jpaReportRepository.save(report);
    }

    @Override
    public void clearCommentReports(List<String> ids) {
        queryDslReportDao.clearCommentReports(ids);
    }
}
