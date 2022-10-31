package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.quiz.command.domain.Quiz;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.kk.picturequizapi.TestFactory.createMockQuiz;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JpaQuizRepositoryTest {

    @Autowired
    QuizRepository quizRepository;

    @Test
    void findById () throws Exception{
        //given
        String quizId = "123";
        Quiz quiz = createMockQuiz();
        quizRepository.save(quiz);
        //when

        Quiz finded = quizRepository.findById(QuizId.of(quizId)).get();
        //then
        assertThat(finded.getAnswer().getName()).isEqualTo("정답");


    }

}