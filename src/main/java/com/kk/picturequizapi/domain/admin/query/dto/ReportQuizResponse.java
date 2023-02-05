package com.kk.picturequizapi.domain.admin.query.dto;


import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearch;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ReportQuizResponse {

    private QuizSearch targetInfo;
    private Map<String, Long> nOfReports;


    public ReportQuizResponse(Quiz quiz, List<ReportCount> reports) {
        this.targetInfo = new QuizSearch(quiz);
        nOfReports = new LinkedHashMap<>();
        for (ReportCount report : reports) {
            nOfReports.put(report.getReportType().getKor(), report.getCount());
        }
    }
}
