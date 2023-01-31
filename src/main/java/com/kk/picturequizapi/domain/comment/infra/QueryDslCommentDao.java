package com.kk.picturequizapi.domain.comment.infra;


import static com.kk.picturequizapi.domain.comment.command.domain.QComment.comment;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.QComment;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Repository
public class QueryDslCommentDao {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public QueryDslCommentDao(EntityManager em){
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public Optional<Long> findParentOrder(CommentId parentId){

        Long order = jpaQueryFactory.select(comment.commentOrder.orderNum)
                .from(comment)
                .where(comment.commentId.eq(parentId))
                .fetchOne();

        log.info("Parent Order = {} ", order);

        return Optional.ofNullable(order);

    }

    public Optional<Long> findQuizLastOrder(QuizId quizId){

        Long last = jpaQueryFactory.select(comment.commentOrder.orderNum)
                .from(comment)
                .where(comment.quizId.eq(quizId))
                .orderBy(comment.commentOrder.orderNum.desc())
                .fetchFirst();

        log.info("Quiz Last Order = {} ", last);

        return Optional.ofNullable(last);

    }

}
