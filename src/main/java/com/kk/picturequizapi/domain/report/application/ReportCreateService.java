package com.kk.picturequizapi.domain.report.application;

import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportContent;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.Reporter;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.report.domain.TargetValidateService;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import com.kk.picturequizapi.domain.report.exception.AlreadyReportedQuizException;
import com.kk.picturequizapi.domain.report.exception.BothTypeMismatchException;
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
    private final TargetValidateService targetValidateService;

    public void createReport(ReportCreateRequest dto) {
        Users users = getUserInfoFromAuthentication();
        validateCanCreateReport(dto,users);
        createAndSaveReport(dto, users);
    }

    private void createAndSaveReport(ReportCreateRequest dto, Users users) {
        reportRepository.save(createReportEntity(dto, users));
    }

    private void validateCanCreateReport(ReportCreateRequest dto, Users users){
        validateType(dto.getTargetType(), dto.getReportType());
        validateTargetId(dto);
        inspectDuplicateReport(dto, users);
    }

    private void validateType(TargetType targetType, ReportType reportType){
        if(!reportType.validateTargetType(targetType))
            throw new BothTypeMismatchException();

    }

    private void validateTargetId(ReportCreateRequest dto) {
        targetValidateService.validateTargetId(TargetId.of(dto.getTargetId()), dto.getTargetType());
    }

    private Report createReportEntity(ReportCreateRequest dto, Users users) {
        Reporter reporter = Reporter.of(UserId.of(users.getId()), users.getNickname());
        ReportContent reportContent = ReportContent.of(dto.getReportType(),
                dto.getReportDescription());
        return Report.of(ReportId.of(reportRepository.nextId()), TargetId.of(dto.getTargetId()),
                reporter, reportContent,dto.getTargetType());
    }

    private void inspectDuplicateReport(ReportCreateRequest dto, Users users) {
        if (reportRepository.isExistReport(TargetId.of(dto.getTargetId()),dto.getTargetType(), UserId.of(users.getId())
                , dto.getReportType())) {
            throw new AlreadyReportedQuizException(); // 중복 신고 예외 처리
        }
    }

    private Users getUserInfoFromAuthentication() {
        return (Users) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
