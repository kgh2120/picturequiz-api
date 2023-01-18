package com.kk.picturequizapi.domain.report;

import static com.kk.picturequizapi.TestFactory.createUser;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.report.application.ReportCreateService;
import com.kk.picturequizapi.domain.report.domain.ReportRepository;
import com.kk.picturequizapi.domain.report.domain.ReportType;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import com.kk.picturequizapi.domain.report.exception.AlreadyReportedQuizException;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
class ReportCreateServiceTest {



    @Test
    void 신고_생성 () throws Exception{
        //given
        ReportCreateRequest dto = new ReportCreateRequest("foo", ReportType.ETC, "bar");
        ReportRepository reportRepository = new FakeReportRepository();


        Users user = createUser("login", "pw");
        SecurityContextHolder.getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(user, user.getPassword()));


        ReportCreateService service = new ReportCreateService(reportRepository); // ReportRepo, UserRepo
        //when
        service.createReport(dto);

        //then

        boolean dup = reportRepository.isExistReport(QuizId.of(dto.getQuizId())
        , UserId.of(1L),ReportType.ETC); // QuizId, RepoterId, ReportType이 같은 것을 체크.

        assertThat(dup).isTrue();

    }

    @Test
    void 신고_생성_중복_예외 () throws Exception{
        //given
        ReportCreateRequest dto = new ReportCreateRequest("foo", ReportType.ETC, "bar");
        ReportRepository reportRepository = new FakeReportRepository();


        Users user = createUser("login", "pw");
        SecurityContextHolder.getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(user, user.getPassword()));

        ReportCreateService service = new ReportCreateService(reportRepository); // ReportRepo, UserRepo
        service.createReport(dto);
        //when //then

        assertThatThrownBy(() -> service.createReport(dto))
                .isInstanceOf(AlreadyReportedQuizException.class);
    }



}
