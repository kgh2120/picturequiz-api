package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.quiz.command.domain.QQuiz;
import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.quiz.exception.NoMoreQuizDataException;
import com.kk.picturequizapi.domain.quiz.exception.NoSearchResultException;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearch;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchOrderCondition;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.kk.picturequizapi.domain.quiz.command.domain.QQuiz.*;
import static com.kk.picturequizapi.domain.quiz.command.domain.QQuizTag.quizTag;
import static org.springframework.util.StringUtils.hasText;

@Transactional
@Repository
public class QueryDslQuizSearchDao implements QuizSearchDao {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    private final Environment env;
    private int quizLimit;

    @Autowired
    public QueryDslQuizSearchDao(EntityManager em, Environment env) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
        this.env = env;
        quizLimit = Integer.parseInt(this.env.getProperty("quiz.limit"));
    }

    @Override
    public QuizSearchResponse searchQuizByCondition(QuizSearchCondition cond, int pageNum) {

        JPAQuery<Quiz> where = queryFactory.selectFrom(quiz).distinct()
                .leftJoin(quiz.quizTags, quizTag).fetchJoin()
                .where(buildCondition(cond));

        if (cond.getTagNames() != null) { // ??
            where
                    .groupBy(quiz.quizId)
                    .having(quiz.quizId.count().goe((long) cond.getTagNames().size()));
        }


        List<Quiz> quizzes = where
                .orderBy(quizOrder(cond.getOrderCondition()))
                .offset(pageNum * quizLimit)
                .limit(quizLimit + 1)
                .fetch();

        if (quizzes.isEmpty()) {
            if (pageNum == 0)
                throw new NoSearchResultException();
            else
                throw new NoMoreQuizDataException();
        }

        boolean hasNext = quizzes.size() > quizLimit;


        List<QuizSearch> searches = new ArrayList<>();
        quizzes.forEach(q -> searches.add(new QuizSearch(q)));

        return new QuizSearchResponse(searches, pageNum + 1, hasNext);
    }

    @Override
    public QuizSearchResponse searchMyQuizzes(UserId userId, int pageNum) {


        List<Quiz> quizzes = queryFactory.selectFrom(quiz)
                .distinct()
                .leftJoin(quiz.quizTags, quizTag).fetchJoin()
                .where(quiz.author.userId.eq(userId))
                .offset(pageNum * quizLimit)
                .limit(quizLimit + 1)
                .fetch();

        if (quizzes.isEmpty()) {
            if (pageNum == 0)
                throw new NoSearchResultException();
            else
                throw new NoMoreQuizDataException();
        }

        boolean hasNext = quizzes.size() > quizLimit;


        List<QuizSearch> searches = new ArrayList<>();
        quizzes.forEach(q -> searches.add(new QuizSearch(q)));

        return new QuizSearchResponse(searches, pageNum + 1, hasNext);
    }


    private OrderSpecifier<?> quizOrder(QuizSearchOrderCondition orderCond) {
        if (orderCond == null)
            return new OrderSpecifier(Order.DESC, quiz.viewCount);

        switch (orderCond) {
            case POPULAR:
                return new OrderSpecifier(Order.DESC, quiz.viewCount);
            case RECENT:
                return new OrderSpecifier(Order.DESC, quiz.createdDate);
            default:
                return new OrderSpecifier(Order.DESC, quiz.viewCount);
        }
    }


    private BooleanBuilder buildCondition(QuizSearchCondition cond) {
        BooleanBuilder bb = new BooleanBuilder();

        if (hasText(cond.getAnswerName())) {
            bb.and(quiz.answer.name.eq(cond.getAnswerName()));
        }

        if (cond.getTagNames() != null && !cond.getTagNames().isEmpty()) {
            bb.and(quizTag.name.in(cond.getTagNames()));
        }
        return bb;
    }


}
