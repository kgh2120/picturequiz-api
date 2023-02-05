package com.kk.picturequizapi.domain.admin.query.dao;

import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportFilter;
import com.kk.picturequizapi.domain.admin.query.dto.ReportOrderCondition;
import com.kk.picturequizapi.domain.admin.query.dto.ReportQuizResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportRetrieveResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportTargetRetrieveResponse;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import java.time.LocalDate;

public interface AdminReportDao {

    CreateCountResponse retrieveCreateCount(LocalDate date);

    ReportRetrieveResponse retrieveReports(ReportFilter filter, ReportOrderCondition orderCond, long pageNum);

    ReportTargetRetrieveResponse retrieveReportTargets(ReportFilter type, ReportOrderCondition orderCond, long pageNum, int min);

    ReportQuizResponse retrieveQuiz(TargetId quizId);
}
