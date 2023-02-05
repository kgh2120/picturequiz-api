package com.kk.picturequizapi.domain.admin.infra;

import static com.kk.picturequizapi.domain.quiz.command.domain.QQuiz.quiz;

import com.kk.picturequizapi.domain.admin.query.dao.AdminQuizDao;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.QCreateCount;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public class QueryDslAdminQuizDao implements AdminQuizDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Autowired
    public QueryDslAdminQuizDao(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
    @Override
    public CreateCountResponse retrieveCreateCount(LocalDate date) {
        return new CreateCountResponse(date, getCreateCount(date)) ;

    }

    private List<CreateCount> getCreateCount(LocalDate date) {
        return jpaQueryFactory.select(new QCreateCount(quiz.count(), quiz.createdDate))
                .from(quiz)
                .where(quiz.createdDate.after(date.minusWeeks(1))
                        .and(quiz.createdDate.before(date.plusDays(1))))
                .groupBy(quiz.createdDate)
                .fetch();
    }
}
