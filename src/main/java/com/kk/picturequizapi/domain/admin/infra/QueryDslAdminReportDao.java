package com.kk.picturequizapi.domain.admin.infra;

import static com.kk.picturequizapi.domain.report.domain.QReport.report;

import com.kk.picturequizapi.domain.admin.query.dao.AdminReportDao;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.QCreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.QReportResponse;
import com.kk.picturequizapi.domain.admin.query.dto.QReportTarget;
import com.kk.picturequizapi.domain.admin.query.dto.ReportFilter;
import com.kk.picturequizapi.domain.admin.query.dto.ReportOrderCondition;
import com.kk.picturequizapi.domain.admin.query.dto.ReportResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportRetrieveResponse;
import com.kk.picturequizapi.domain.admin.query.dto.ReportTarget;
import com.kk.picturequizapi.domain.admin.query.dto.ReportTargetRetrieveResponse;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Override
    public ReportRetrieveResponse retrieveReports(ReportFilter filter,ReportOrderCondition orderCond, long pageNum) {
        List<ReportResponse> responses = jpaQueryFactory.select(
                        new QReportResponse(report.reportId.id, report.reporter.nickname,
                                report.targetId.id,
                                report.targetType, report.reportContent.reportType,
                                report.reportContent.desc))
                .from(report)
                .where(buildCondition(filter))
                .orderBy(reportOrder(orderCond))
                .offset(pageNum * 10)
                .limit(10)
                .fetch();

        Long lastPage = jpaQueryFactory.select(Wildcard.count)
                .from(report)
                .where(buildCondition(filter))
                .fetchOne() / 10 + 1;

        return new ReportRetrieveResponse(responses, pageNum +1, lastPage);
    }

    @Override
    public ReportTargetRetrieveResponse retrieveReportTargets(ReportFilter type, ReportOrderCondition orderCond, long pageNum,
            int min) {

        List<ReportTarget> targets = jpaQueryFactory.select(
                        new QReportTarget(report.targetId.id, report.targetType, report.targetId.count()))
                .from(report)
                .where(buildCondition(type))
                .groupBy(report.targetId)
                .having(report.targetId.count().goe(min))
                .orderBy(reportOrder(orderCond))
                .offset(pageNum * 10)
                .limit(10)
                .fetch();

        AtomicLong total = new AtomicLong();
        jpaQueryFactory.select(report.targetId.count())
                .from(report)
                .where(buildCondition(type))
                .groupBy(report.targetId)
                .having(report.targetId.count().goe(min))
                .fetch().forEach(total::addAndGet);
        long lastPage = total.get() / 10 + 1;

        log.info("total = {}", total);

        return new ReportTargetRetrieveResponse(targets, pageNum+1, lastPage);
    }

    private List<CreateCount> getCreateCount(LocalDate date) {
        return jpaQueryFactory.select(new QCreateCount(report.count(), report.createdDate))
                .from(report)
                .where(report.createdDate.after(date.minusWeeks(1))
                        .and(report.createdDate.before(date.plusDays(1))))
                .groupBy(report.createdDate)
                .fetch();
    }
    private BooleanBuilder buildCondition(ReportFilter filter) {
        BooleanBuilder bb = new BooleanBuilder();
        switch (filter){
            case QUIZ: return bb.and(report.targetType.eq(TargetType.QUIZ));
            case COMMENT: return bb.and(report.targetType.eq(TargetType.COMMENT));
            default:return bb;
        }
    }



    private OrderSpecifier<?> reportOrder(ReportOrderCondition orderCond) {
        if (orderCond == null)
            return new OrderSpecifier(Order.DESC, report.createdDate);
        if (orderCond != ReportOrderCondition.RECENT) {
            return new OrderSpecifier(Order.DESC, report.createdDate);
        }
        return new OrderSpecifier(Order.ASC, report.createdDate);
    }
}
