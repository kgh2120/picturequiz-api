package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuizRepositoryTest {

    @Autowired QuizRepository quizRepository;
    @Autowired
    EntityManager em;

    // 퀴즈 데이터 생성에 필요한 것.
    // 1. 퀴즈 ID
    // 2. 작성자
    // 3. 캐릭터
    // 4. 태그?

    @Test
    void saveQuiz () throws Exception{
        //given
        QuizData quiz = new QuizData(QuizId.of(quizRepository.nextId()),
                new Author(UserId.of(1L), "Kim"),
                new Picture("ex"),
                new Answer(CharacterId.of(1L), "Test"),
                new ArrayList<QuizTag>()
                );
        quizRepository.save(quiz);
        //when

        //then
        QuizData quizData = em.find(QuizData.class, QuizId.of(quiz.getQuizId().getId()));
        assertThat(quiz.getQuizId()).isEqualTo(quizData.getQuizId());

    }
    @Test
    void nextId () throws Exception{
        //given
        String id = quizRepository.nextId();

        //when
        String nextId = quizRepository.nextId();

        //then
        assertThat(id).isNotEqualTo(nextId);

    }
}