package com.kk.picturequizapi.domain.admin.infra;

import static com.kk.picturequizapi.domain.comment.command.domain.QComment.comment;
import static com.kk.picturequizapi.domain.users.entity.QUsers.users;

import com.kk.picturequizapi.domain.admin.query.dao.AdminCommentDao;
import com.kk.picturequizapi.domain.admin.query.dao.AdminQuizDao;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.QCreateCount;
import com.kk.picturequizapi.domain.comment.command.domain.QComment;
import com.kk.picturequizapi.domain.users.entity.UserRole;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Repository
public class QueryDslAdminCommentDao implements AdminCommentDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Autowired
    public QueryDslAdminCommentDao(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public CreateCountResponse retrieveCreateCount(LocalDate date) {
        return new CreateCountResponse(date, getCreateCount(date));

    }

    private List<CreateCount> getCreateCount(LocalDate date) {
        return jpaQueryFactory.select(new QCreateCount(comment.count(), comment.createdDate))
                .from(comment)
                .where(comment.createdDate.after(date.minusWeeks(1))
                        .and(comment.createdDate.before(date.plusDays(1))))
                .groupBy(comment.createdDate)
                .fetch();

    }
}
