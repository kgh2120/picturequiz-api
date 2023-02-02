package com.kk.picturequizapi.domain.comment.infra;


import static com.kk.picturequizapi.domain.comment.command.domain.QComment.comment;
import static com.kk.picturequizapi.domain.comment.command.domain.QCommentRecommend.commentRecommend;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.QComment;
import com.kk.picturequizapi.domain.comment.command.domain.QCommentRecommend;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearch;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import com.kk.picturequizapi.domain.comment.query.dto.QCommentSearch;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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
        return Optional.ofNullable(order);

    }

    public Optional<Long> findQuizLastOrder(QuizId quizId){

        Long last = jpaQueryFactory.select(comment.commentOrder.orderNum)
                .from(comment)
                .where(comment.quizId.eq(quizId))
                .orderBy(comment.commentOrder.orderNum.desc())
                .fetchFirst();
        return Optional.ofNullable(last);

    }

    public CommentSearchResult retrieveComments(QuizId quizId, UserId userId,long pageNum){

        List<CommentSearch> searches = jpaQueryFactory.select(
                        new QCommentSearch(comment.commentId.commentId,
                                comment.parentId.commentId,
                                comment.author.userId.id,
                                comment.author.nickname,
                                comment.commentContent.content,
                                comment.commentOrder.orderNum,
                                comment.recommend.numOfRecommend,
                                comment.recommend.numOfNotRecommend,
                                commentRecommend.recommendStatus.recommend,
                                commentRecommend.recommendStatus.notRecommend))
                .distinct()
                .from(comment)
                .leftJoin(comment.commentRecommends, commentRecommend)
                .on(commentRecommend.userId.eq(userId))
                .where(comment.quizId.eq(quizId))
                .orderBy(comment.commentOrder.orderNum.asc())
                .offset(pageNum)
                .limit(10)
                .fetch();

        log.info("comments = {} ",searches);

        Long lastPage = jpaQueryFactory.select(Wildcard.count)
                .from(comment)
                .where(comment.quizId.eq(quizId))
                .fetch().get(0)/10 + 1;

        return new CommentSearchResult(searches, pageNum+1, lastPage);
    }

}
