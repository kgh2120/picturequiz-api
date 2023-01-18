package com.kk.picturequizapi.domain.report;

import static org.assertj.core.api.Assertions.*;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.domain.Report;
import com.kk.picturequizapi.domain.report.domain.ReportContent;
import com.kk.picturequizapi.domain.report.domain.ReportId;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.domain.Reporter;
import com.kk.picturequizapi.domain.report.repository.JpaReportRepository;
import com.kk.picturequizapi.domain.users.entity.UserId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class JpaReportRepositoryTest {

    @Autowired
    JpaReportRepository jpaReportRepository;


    @Test
    void 중복검사테스트_False () throws Exception{
        //given
        QuizId quizId = QuizId.of("foo");
        UserId userId = UserId.of(1L);
        //when
        boolean exists = jpaReportRepository.existsReportByQuizIdAndReporterUserIdAndAndReportContent_ReportType(quizId, userId,
                ReportType.ETC);
        //then
        assertThat(exists).isFalse();
    }

    @Test
    void 중복검사테스트_True () throws Exception{
        //given
        QuizId quizId = QuizId.of("foo");
        UserId userId = UserId.of(1L);

        Report report = Report.of(ReportId.of("foo"), quizId, Reporter.of(userId, "nickname")
                , ReportContent.of(ReportType.ETC, ""));
        jpaReportRepository.save(report);
        //when
        boolean exists = jpaReportRepository.existsReportByQuizIdAndReporterUserIdAndAndReportContent_ReportType(quizId, userId,
                ReportType.ETC);
        //then
        assertThat(exists).isTrue();
    }
}
