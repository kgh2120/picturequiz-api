package com.kk.picturequizapi.domain.report.domain;

import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ReportContent {


    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    private String desc;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportContent that = (ReportContent) o;
        return reportType == that.reportType && Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportType, desc);
    }

    public static ReportContent of(ReportType type, String desc) {
        return new ReportContent(type,desc);
    }

    public ReportType getReportType() {
        return reportType;
    }
}
