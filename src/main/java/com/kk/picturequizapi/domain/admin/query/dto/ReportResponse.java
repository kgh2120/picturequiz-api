package com.kk.picturequizapi.domain.admin.query.dto;

import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReportResponse {

    private String reportId;
    private String reporterNickname;
    private String targetId;
    private String targetType;
    private String reportType;
    private String desc;

    @QueryProjection
    public ReportResponse(String reportId, String reporterNickname, String targetId,
            TargetType targetType,
            ReportType reportType, String desc) {
        this.reportId = reportId;
        this.reporterNickname = reporterNickname;
        this.targetId = targetId;
        this.targetType = targetType.getKor();
        this.reportType = reportType.getKor();
        this.desc = desc;
    }
}
