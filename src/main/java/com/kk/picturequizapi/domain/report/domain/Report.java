package com.kk.picturequizapi.domain.report.domain;


import com.kk.picturequizapi.domain.report.exception.InputNullDataOnTargetId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReportContent;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReportId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReporter;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnTargetType;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.global.jpa.BaseEntity;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report extends BaseEntity {

    @EmbeddedId
    private ReportId reportId;

    @Embedded
    private TargetId targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Embedded
    private Reporter reporter;

    @Embedded
    private ReportContent reportContent;

    public static Report of(ReportId reportId, TargetId targetId, Reporter reporter, ReportContent reportContent, TargetType targetType) {
        validateAttribute(reportId, targetId, reporter, reportContent, targetType);
        return new Report(reportId,targetId,targetType,reporter,reportContent);
    }

    private static void validateAttribute(ReportId reportId, TargetId targetId, Reporter reporter,
            ReportContent reportContent, TargetType targetType){
        validateTargetId(targetId);
        validateReporter(reporter);
        validateReportId(reportId);
        validateReportContent(reportContent);
        validateTargetType(targetType);

    }

    private static void validateTargetType(TargetType targetType) {
        if(targetType == null)
            throw new InputNullDataOnTargetType();
    }

    private static void validateReportId(ReportId reportId){
        if(reportId == null)
            throw new InputNullDataOnReportId();
    }
    private static void validateTargetId(TargetId targetId) {
        if(targetId == null)
            throw new InputNullDataOnTargetId();
    }

    private static void validateReporter(Reporter reporter){
        if(reporter == null)
            throw new InputNullDataOnReporter();

    }
    private static void validateReportContent(ReportContent reportContent){
        if(reportContent == null)
            throw new InputNullDataOnReportContent();
    }

    public String getReportIdToString() {
        return this.reportId.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(reportId, report.reportId) && Objects.equals(targetId,
                report.targetId) && Objects.equals(reporter, report.reporter)
                && Objects.equals(reportContent, report.reportContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, targetId, reporter, reportContent);
    }

    public TargetId getTargetId() {
        return this.targetId;
    }

    public UserId getReporterId() {
        return reporter.getUserId();
    }

    public ReportType getReportType(){
        return reportContent.getReportType();
    }

    public TargetType getTargetType() {
        return targetType;
    }
}
