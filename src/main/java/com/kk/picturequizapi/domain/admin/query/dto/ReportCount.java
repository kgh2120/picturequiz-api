package com.kk.picturequizapi.domain.admin.query.dto;

import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReportCount {

    private ReportType reportType;
    private long count;

    @QueryProjection
    public ReportCount(ReportType reportType, long count) {
        this.reportType = reportType;
        this.count = count;
    }
}
