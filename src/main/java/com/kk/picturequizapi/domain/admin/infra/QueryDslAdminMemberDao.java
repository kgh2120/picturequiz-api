package com.kk.picturequizapi.domain.admin.infra;

import static com.kk.picturequizapi.domain.users.entity.QUsers.users;

import com.kk.picturequizapi.domain.admin.query.dao.AdminMemberDao;
import com.kk.picturequizapi.domain.admin.query.dto.MemberCreateCountResponse;
import com.kk.picturequizapi.domain.admin.query.dto.QMemberCreateCountResponse;
import com.kk.picturequizapi.domain.users.entity.UserRole;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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
    public List<MemberCreateCountResponse> retrieveCreateCount(LocalDate date) {
        return jpaQueryFactory.select(new QMemberCreateCountResponse(users.count(),users.createdDate))
                .from(users)
                .where(users.createdDate.after(date.minusWeeks(1))
                        .and(users.createdDate.before(date.plusDays(1)))
                        .and(users.role.eq(UserRole.ROLE_USER)))
                .groupBy(users.createdDate)
                .fetch();
    }
}
