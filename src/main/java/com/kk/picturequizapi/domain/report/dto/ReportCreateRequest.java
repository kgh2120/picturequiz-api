package com.kk.picturequizapi.domain.report.dto;

import com.kk.picturequizapi.domain.report.domain.ReportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReportCreateRequest {

    private String quizId;
    private ReportType reportType;
    private String reportDescription;



}
