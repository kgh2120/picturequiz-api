package com.kk.picturequizapi.domain.admin.infra;

import static com.kk.picturequizapi.domain.quiz.command.domain.QQuiz.quiz;
import static com.kk.picturequizapi.domain.report.domain.QReport.report;

import com.kk.picturequizapi.domain.admin.query.dao.AdminReportDao;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.QCreateCount;
import com.kk.picturequizapi.domain.report.domain.QReport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class QueryDslAdminReportDao implements AdminReportDao {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Autowired
    public QueryDslAdminReportDao(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
    @Override
    public CreateCountResponse retrieveCreateCount(LocalDate date) {
        return new CreateCountResponse(date, getCreateCount(date)) ;

    }
    private List<CreateCount> getCreateCount(LocalDate date) {
        return jpaQueryFactory.select(new QCreateCount(report.count(), report.createdDate))
                .from(report)
                .where(report.createdDate.after(date.minusWeeks(1))
                        .and(report.createdDate.before(date.plusDays(1))))
                .groupBy(report.createdDate)
                .fetch();
    }
}
