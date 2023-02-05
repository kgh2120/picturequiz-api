package com.kk.picturequizapi.domain.admin.infra;


import static com.kk.picturequizapi.domain.comment.command.domain.QComment.comment;
import static com.kk.picturequizapi.domain.quiz.command.domain.QQuiz.quiz;
import static com.kk.picturequizapi.domain.users.entity.QUsers.users;

import com.kk.picturequizapi.domain.admin.query.dao.AdminMemberDao;
import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberInfo;
import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberPageRequest;
import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberPageResponse;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.MemberOrderCondition;
import com.kk.picturequizapi.domain.admin.query.dto.QAdminMemberInfo;
import com.kk.picturequizapi.domain.admin.query.dto.QCreateCount;

import com.kk.picturequizapi.domain.users.entity.UserRole;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
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
public class QueryDslAdminMemberDao implements AdminMemberDao {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Autowired
    public QueryDslAdminMemberDao(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }


    @Override
    public CreateCountResponse retrieveCreateCount(LocalDate date) {
        return new CreateCountResponse(date, getCreateCount(date)) ;

    }
    private List<CreateCount> getCreateCount(LocalDate date) {
        return jpaQueryFactory.select(new QCreateCount(users.count(), users.createdDate))
                .from(users)
                .where(users.createdDate.after(date.minusWeeks(1))
                        .and(users.createdDate.before(date.plusDays(1)))
                        .and(users.role.eq(UserRole.ROLE_USER)))
                .groupBy(users.createdDate)
                .fetch();

    }
    @Override
    public AdminMemberPageResponse retrieveMemberAdminPage(AdminMemberPageRequest dto) {


        List<AdminMemberInfo> memberInfos = jpaQueryFactory.select(
                        new QAdminMemberInfo(users.id, users.nickname, users.authEmail,
                                users.createdDate, quiz.count(), comment.count()))
                .from(users)
                .leftJoin(quiz).on(quiz.author.userId.id.eq(users.id))
                .leftJoin(comment).on(comment.author.userId.id.eq(users.id))
                .groupBy(users.id)
                .orderBy(memberOrder(dto.getOrderCondition()))
                .offset(dto.getPageNum() * 10)
                .limit(10)
                .fetch();

        Long lastPage = jpaQueryFactory.select(users.count())
                .from(users).fetchOne()/10 + 1;

        return new AdminMemberPageResponse(memberInfos, dto.getPageNum() +1, lastPage );
    }

    private OrderSpecifier<?> memberOrder(MemberOrderCondition orderCond) {
        if (orderCond == null)
            return new OrderSpecifier(Order.DESC, users.createdDate);

        switch (orderCond) {
            case OLDER: return new OrderSpecifier(Order.ASC, users.createdDate);
            case QUIZ_ASC: return new OrderSpecifier(Order.ASC, quiz.count());
            case QUIZ_DESC: return new OrderSpecifier(Order.DESC, quiz.count());
            case COMMENT_ASC: return new OrderSpecifier(Order.ASC, comment.count());
            case COMMENT_DESC: return new OrderSpecifier(Order.DESC, comment.count());
            default: return new OrderSpecifier(Order.DESC, users.createdDate);
        }
    }
}
