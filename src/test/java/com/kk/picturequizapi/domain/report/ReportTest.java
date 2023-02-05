package com.kk.picturequizapi.domain.report;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportContent;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.Reporter;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnTargetId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReportContent;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReportId;
import com.kk.picturequizapi.domain.report.exception.InputNullDataOnReporter;
import com.kk.picturequizapi.domain.users.entity.UserId;
import org.junit.jupiter.api.Test;

class ReportTest {



    @Test
    void 예외_사고ID_NULL () throws Exception{
        assertThatThrownBy(() -> Report.of(null, TargetId.of("foo"),
                Reporter.of(UserId.of(1L), "bar"),
                ReportContent.of(ReportType.INAPPROPRIATE_PICTURE, "사진이 이상해요"), TargetType.QUIZ))
                .isInstanceOf(InputNullDataOnReportId.class);
    }
    @Test
    void 예외_퀴즈ID_NULL () throws Exception{
        assertThatThrownBy(() -> Report.of(ReportId.of("foo"), null,
                Reporter.of(UserId.of(1L), "bar"),
                ReportContent.of(ReportType.INAPPROPRIATE_PICTURE, "사진이 이상해요"), TargetType.QUIZ))
                .isInstanceOf(InputNullDataOnTargetId.class);

    }
    @Test
    void 예외_신고자_NULL () throws Exception{
        assertThatThrownBy(() -> Report.of(ReportId.of("foo"), TargetId.of("foo"),
               null,
                ReportContent.of(ReportType.INAPPROPRIATE_PICTURE, "사진이 이상해요"), TargetType.QUIZ))
                .isInstanceOf(InputNullDataOnReporter.class);

    }
    @Test
    void 예외_신고내용_NULL () throws Exception{
        assertThatThrownBy(() -> Report.of(ReportId.of("foo"), TargetId.of("foo"),
                Reporter.of(UserId.of(1L), "bar"),
                null, TargetType.QUIZ))
                .isInstanceOf(InputNullDataOnReportContent.class);
    }

}
