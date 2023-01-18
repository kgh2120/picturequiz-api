package com.kk.picturequizapi.domain.report.domain;


import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnQuizId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReportContent;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReportId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReporter;
import com.kk.picturequizapi.domain.users.entity.UserId;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {

    @EmbeddedId
    private ReportId reportId;

    @Embedded
    private QuizId quizId;

    @Embedded
//    @AttributeOverride(name = "userId.id", column = @Column(name = "reporter_id"))
    private Reporter reporter;

    @Embedded
    private ReportContent reportContent;

    public static Report of(ReportId reportId, QuizId quizId, Reporter reporter, ReportContent reportContent) {
        validateAttribute(reportId, quizId, reporter, reportContent);
        return new Report(reportId,quizId,reporter,reportContent);
    }

    private static void validateAttribute(ReportId reportId, QuizId quizId, Reporter reporter,
            ReportContent reportContent){
        validateQuizId(quizId);
        validateReporter(reporter);
        validateReportId(reportId);
        validateReportContent(reportContent);

    }

    private static void validateReportId(ReportId reportId){
        if(reportId == null)
            throw new InputNullDataOnReportId();
    }
    private static void validateQuizId(QuizId quizId) {
        if(quizId == null)
            throw new InputNullDataOnQuizId();
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
        return Objects.equals(reportId, report.reportId) && Objects.equals(quizId,
                report.quizId) && Objects.equals(reporter, report.reporter)
                && Objects.equals(reportContent, report.reportContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportId, quizId, reporter, reportContent);
    }

    public QuizId getQuizId() {
        return this.quizId;
    }

    public UserId getReporterId() {
        return reporter.getUserId();
    }

    public ReportType getReportType(){
        return reportContent.getReportType();
    }
}
