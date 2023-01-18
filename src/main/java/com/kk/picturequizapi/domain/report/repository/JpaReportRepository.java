package com.kk.picturequizapi.domain.report.repository;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.users.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaReportRepository extends JpaRepository<Report, ReportId> {

    boolean existsReportByQuizIdAndReporterUserIdAndAndReportContent_ReportType(QuizId of, UserId userId, ReportType etc);

}
