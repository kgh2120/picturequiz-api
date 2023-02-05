package com.kk.picturequizapi.domain.admin.query.ui;

import com.kk.picturequizapi.domain.admin.query.dao.AdminReportDao;
import com.kk.picturequizapi.domain.admin.query.dto.ReportCommentResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportFilter;
import com.kk.picturequizapi.domain.admin.query.dto.ReportOrderCondition;
import com.kk.picturequizapi.domain.admin.query.dto.ReportQuizResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportRetrieveResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportTargetRetrieveResponse;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
public class AdminRetrieveReportController {

    private final AdminReportDao adminReportDao;

    @GetMapping("/admin/reports/{type}")
    public ResponseEntity<ReportRetrieveResponse> retrieveReports(
            @PathVariable("type")ReportFilter filter,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") @Min(0) long pageNum,
            @RequestParam(value = "order", required = false, defaultValue = "RECENT") ReportOrderCondition orderCond) {

        ReportRetrieveResponse retrieveResponse = adminReportDao.retrieveReports(filter,
                orderCond, pageNum);
        return ResponseEntity.ok(retrieveResponse);
    }

    @GetMapping("/admin/reports/target/{type}")
    public ResponseEntity<ReportTargetRetrieveResponse> retrieveReportTarget(
            @PathVariable("type") ReportFilter type,
            @RequestParam(value = "pageNum", required = false, defaultValue = "0") @Min(0) long pageNum,
            @RequestParam(value = "min", required = false, defaultValue = "0") int min,
            @RequestParam(value = "order", required = false, defaultValue = "RECENT")  ReportOrderCondition orderCond){
        ReportTargetRetrieveResponse response = adminReportDao.retrieveReportTargets(
                type, orderCond, pageNum, min);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/reports/quiz/{quizId}")
    public ResponseEntity<ReportQuizResponse> retrieveReportedQuiz(@PathVariable("quizId") String quizId) {
        ReportQuizResponse response = adminReportDao.retrieveReportedQuiz(TargetId.of(quizId));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/reports/comments/{commentId}")
    public ResponseEntity<ReportCommentResponse> retrieveReportedComment(@PathVariable("commentId") String commentId) {
        ReportCommentResponse dto = adminReportDao.retrieveReportedComment(TargetId.of(commentId));
        return ResponseEntity.ok(dto);
    }

}
