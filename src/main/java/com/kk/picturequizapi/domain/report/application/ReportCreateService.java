package com.kk.picturequizapi.domain.report.application;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportContent;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.Reporter;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import com.kk.picturequizapi.domain.report.exception.AlreadyReportedQuizException;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
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
        Users users = getUserInfoFromAuthentication();
        inspectDuplicateReport(dto, users);
        reportRepository.save(createReportEntity(dto, users));
    }

    private Report createReportEntity(ReportCreateRequest dto, Users users) {
        Reporter reporter = Reporter.of(UserId.of(users.getId()), users.getNickname());
        ReportContent reportContent = ReportContent.of(dto.getReportType(),
                dto.getReportDescription());
        return Report.of(ReportId.of(reportRepository.nextId()), QuizId.of(dto.getQuizId()),
                reporter, reportContent);
    }

    private void inspectDuplicateReport(ReportCreateRequest dto, Users users) {
        if (reportRepository.isExistReport(QuizId.of(dto.getQuizId()), UserId.of(users.getId())
                , dto.getReportType())) {
            throw new AlreadyReportedQuizException(); // 중복 신고 예외 처리
        }
    }

    private Users getUserInfoFromAuthentication() {
        return (Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
