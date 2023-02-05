package com.kk.picturequizapi.domain.report.dto;

import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportCreateRequest {

    private String targetId;
    private TargetType targetType;
    private ReportType reportType;
    private String reportDescription;



}
