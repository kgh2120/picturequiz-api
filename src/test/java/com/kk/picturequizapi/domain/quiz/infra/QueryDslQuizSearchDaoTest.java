package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import com.kk.picturequizapi.domain.quiz.command.domain.*;
import com.kk.picturequizapi.domain.quiz.query.dao.QuizSearchDao;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.kk.picturequizapi.TestFactory.createMockQuizData;
import static com.kk.picturequizapi.domain.quiz.command.domain.QQuizData.quizData;
import static com.kk.picturequizapi.domain.quiz.command.domain.QQuizTag.quizTag;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.StringUtils.*;

@DataJpaTest
class QueryDslQuizSearchDaoTest {


    @Autowired
    EntityManager em;

    JPAQueryFactory qf;

    @Autowired
    QuizSearchDao quizSearchDao;

    @BeforeEach
    void beforeEach() throws Exception {
        qf = new JPAQueryFactory(em);


    }



    @Test
    void  tdd() throws Exception{
        //given
        QuizData data = createMockQuizData();
        em.persist(data);

        SearchCondition cond = new SearchCondition("정답", null);
        //when
        List<QuizData> fetch = qf.selectFrom(quizData).distinct()
                .join(quizData.quizTags, quizTag)
                .where(buildCondition(cond))
                .fetch();


        for (QuizData f : fetch) {
            System.out.println("[LOG] = " + f);
        }


        //then
    }

    @Test
    void tdd2 () throws Exception{
        //given
        createDatas();
        List<String> tags = new ArrayList<>();
        tags.add("운동");
        tags.add("운동2");
        SearchCondition cond = new SearchCondition("정답",tags);
        //when
        List<QuizData> fetch = qf.selectFrom(quizData).distinct()
                .join(quizData.quizTags, quizTag)
                .where(buildCondition(cond))
                .groupBy(quizData.quizId)
                .having(quizData.quizId.count().goe((long) tags.size()))
                .fetch();


        for (QuizData f : fetch) {
            System.out.println("[LOG] = " + f);
        }
        //then


    }

    @Test
    void tdd3 () throws Exception{
        //given
        createDatas();
        List<String> tags = new ArrayList<>();
        tags.add("운동");

        SearchCondition cond = new SearchCondition("정답",tags);
        //when
        List<QuizData> fetch = qf.selectFrom(quizData).distinct()
                .join(quizData.quizTags, quizTag)
                .where(buildCondition(cond))
                .groupBy(quizData.quizId)
                .having(quizData.quizId.count().goe((long) tags.size()))
                .fetch();


        for (QuizData f : fetch) {
            System.out.println("[LOG] = " + f);
        }
        //then
    }

    @Test
    void tdd4 () throws Exception{
        //given
        createDatas();


        SearchCondition cond = new SearchCondition("정답", Collections.EMPTY_LIST);
        //when
        JPAQuery<QuizData> where = qf.selectFrom(quizData).distinct()
                .join(quizData.quizTags, quizTag)
                .where(buildCondition(cond));

        if (cond.tags != null) {
            where
                    .groupBy(quizData.quizId)
                    .having(quizData.quizId.count().goe((long) cond.tags.size()));
        }

        List<QuizData> fetch = where
                .limit(2)
                .fetch();


        for (QuizData f : fetch) {
            System.out.println("[LOG] = " + f);
        }
        assertThat(fetch.size()).isEqualTo(2);
        //then
    }
    

    @Test
    void searchMyQuiz () throws Exception{
        //given
        createDatas();
        //when
        QuizSearchResponse r = quizSearchDao.searchMyQuizzes(UserId.of(2L), 0);

        //then
        assertThat(r.getQuizzes().size()).isEqualTo(2);
        
    
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ?  quizData.answer.name.eq(name) : null;
    }

    private BooleanBuilder buildCondition(SearchCondition cond) {
        BooleanBuilder bb = new BooleanBuilder();

        if (hasText(cond.name)) {
            bb.and(quizData.answer.name.eq(cond.name));
        }

        if (cond.tags != null && !cond.tags.isEmpty()) {
            bb.and(quizTag.name.in(cond.tags));
        }
        return bb;
    }

    private void createDatas() {
        List<QuizTag> tag = new ArrayList<>();
        tag.add(new QuizTag(TagId.of("111"),"운동"));
        tag.add(new QuizTag(TagId.of("112"),"운동2"));

        em.persist(new QuizData(QuizId.of("1111"), new Author(UserId.of(1L),"작가")
                ,new Picture("/mock"), new Answer(CharacterId.of(1L),"정답"), tag));;

        List<QuizTag> tag2 = new ArrayList<>();
        tag2.add(new QuizTag(TagId.of("222"),"공부"));
        tag2.add(new QuizTag(TagId.of("2222"),"공부2"));
        tag2.add(new QuizTag(TagId.of("111"),"운동"));

        em.persist(new QuizData(QuizId.of("2222"), new Author(UserId.of(1L),"작가")
                ,new Picture("/mock"), new Answer(CharacterId.of(1L),"정답"), tag2));;

        List<QuizTag> tag3 = new ArrayList<>();
        tag3.add(new QuizTag(TagId.of("333"),"독서"));
        tag3.add(new QuizTag(TagId.of("3333"),"독서2"));
        tag3.add(new QuizTag(TagId.of("112"),"운동2"));

        em.persist(new QuizData(QuizId.of("3333"), new Author(UserId.of(2L),"작가2")
                ,new Picture("/mock"), new Answer(CharacterId.of(1L),"정답"), tag3));;

    }

    class SearchCondition{
        String name;
        List<String> tags;

        public SearchCondition(String name, List<String> tags) {
            this.name = name;
            this.tags = tags;
        }
    }
}