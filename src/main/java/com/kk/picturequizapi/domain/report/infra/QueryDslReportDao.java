package com.kk.picturequizapi.domain.report.infra;

import static com.kk.picturequizapi.domain.report.domain.QReport.report;

import com.kk.picturequizapi.domain.report.domain.QReport;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class QueryDslReportDao {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public QueryDslReportDao(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public void clearCommentReports(List<String> ids){
        List<TargetId> targetIds = ids.stream().map(TargetId::of)
                .collect(Collectors.toList());
        jpaQueryFactory.delete(report)
                .where(report.targetType.eq(TargetType.COMMENT)
                        .and(report.targetId.in(targetIds)))
                .execute();
    }

}
