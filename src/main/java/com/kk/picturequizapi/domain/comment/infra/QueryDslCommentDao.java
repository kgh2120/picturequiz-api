package com.kk.picturequizapi.domain.comment.infra;


import static com.kk.picturequizapi.domain.comment.command.domain.QComment.comment;
import static com.kk.picturequizapi.domain.comment.command.domain.QCommentRecommend.commentRecommend;

import com.kk.picturequizapi.domain.comment.command.domain.Comment;
import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.QComment;
import com.kk.picturequizapi.domain.comment.command.domain.QCommentRecommend;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearch;
import com.kk.picturequizapi.domain.comment.query.dto.CommentSearchResult;
import com.kk.picturequizapi.domain.comment.query.dto.QCommentSearch;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.global.exception.CurrentPageBiggerLastPageException;
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
                                commentRecommend.recommendStatus.notRecommend,
                                comment.createdDateTime))
                .distinct()
                .from(comment)
                .leftJoin(comment.commentRecommends, commentRecommend)
                .on(commentRecommend.userId.eq(userId))
                .where(comment.quizId.eq(quizId))
                .orderBy(comment.commentOrder.orderNum.asc(), comment.createdDateTime.asc())
                .offset(pageNum * 10)
                .limit(10)
                .fetch();

        log.info("comments = {} ",searches);

        Long total = jpaQueryFactory.select(Wildcard.count)
                .from(comment)
                .where(comment.quizId.eq(quizId))
                .fetch().get(0);
        Long lastPage = total /10;

        if(total != 0 && total%10 == 0)
            lastPage--;

        if(pageNum > lastPage)
            throw new CurrentPageBiggerLastPageException();

        return new CommentSearchResult(searches,pageNum, pageNum+1, lastPage);
    }

    public void clearCommentOnQuiz(QuizId quizId){
        jpaQueryFactory.delete(comment)
                .where(comment.quizId.eq(quizId))
                .execute();
    }

    public List<Comment> findAllCommentsByUserId(UserId userId) {

        return jpaQueryFactory.selectFrom(comment)
                .where(comment.author.userId.eq(userId))
                .fetch();
    }
}
