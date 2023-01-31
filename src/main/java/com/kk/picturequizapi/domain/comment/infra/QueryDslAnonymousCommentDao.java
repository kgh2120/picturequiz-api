package com.kk.picturequizapi.domain.comment.infra;

import static com.kk.picturequizapi.domain.comment.command.domain.QAnonymousComment.anonymousComment;

import com.kk.picturequizapi.domain.comment.command.domain.AnonymousCommentId;
import com.kk.picturequizapi.domain.comment.command.domain.QAnonymousComment;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class QueryDslAnonymousCommentDao {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    public QueryDslAnonymousCommentDao(EntityManager em){
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }


    public Optional<Long> findLastSequence(QuizId quizId){
        Long sequence = jpaQueryFactory.select(anonymousComment.anonymousSequence.sequence)
                .from(anonymousComment)
                .where(anonymousComment.anonymousCommentId.quizId.eq(quizId))
                .orderBy(anonymousComment.anonymousSequence.sequence.desc())
                .fetchFirst();
        return Optional.ofNullable(sequence);
    }



    public Optional<Long> findUserSequence(@Param("id") AnonymousCommentId anonymousCommentId){
        Long sequence = jpaQueryFactory.select(anonymousComment.anonymousSequence.sequence)
                .from(anonymousComment)
                .where(anonymousComment.anonymousCommentId.eq(anonymousCommentId))
                .fetchFirst();
        return Optional.ofNullable(sequence);
    }

}
