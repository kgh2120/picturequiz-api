package com.kk.picturequizapi.domain.report.application;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportContent;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.Reporter;
import com.kk.picturequizapi.domain.report.domain.ReporterService;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReportCreateService {

    private final ReportRepository reportRepository;

    public void createReport(ReportCreateRequest dto) {

        Users users = (Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (reportRepository.isExistReport(QuizId.of(dto.getQuizId()), UserId.of(users.getId())
                , dto.getReportType())) {
            throw new RuntimeException(); // 중복 신고 예외 처리
        }

        Reporter reporter = Reporter.of(UserId.of(users.getId()), users.getNickname());
        ReportContent reportContent = ReportContent.of(dto.getReportType(),
                dto.getReportDescription());
        Report report = Report.of(ReportId.of(reportRepository.nextId()), QuizId.of(dto.getQuizId()),
                reporter, reportContent);
        reportRepository.save(report);
    }
}
