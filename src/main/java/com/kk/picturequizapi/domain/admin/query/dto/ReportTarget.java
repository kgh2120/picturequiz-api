package com.kk.picturequizapi.domain.admin.query.dto;

import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
public class ReportTarget {

    private String targetId;
    private String targetType;
    private long nOfReports;

    @QueryProjection
    public ReportTarget(String targetId, TargetType targetType, long nOfReports) {
        this.targetId = targetId;
        this.targetType = targetType.getKor();
        this.nOfReports = nOfReports;
    }
}
