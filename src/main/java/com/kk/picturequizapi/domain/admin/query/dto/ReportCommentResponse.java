package com.kk.picturequizapi.domain.admin.query.dto;

import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearch;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReportCommentResponse {

    private ReportedComment targetInfo;
    private Map<String, Long> nOfReports;


    public ReportCommentResponse(ReportedComment targetInfo, List<ReportCount> reports) {
        this.targetInfo = targetInfo;
        nOfReports = new LinkedHashMap<>();
        for (ReportCount report : reports) {
            nOfReports.put(report.getReportType().getKor(), report.getCount());
        }
    }

}
