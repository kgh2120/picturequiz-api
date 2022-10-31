package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import com.kk.picturequizapi.domain.quiz.exception.QuizNotFoundByIdException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.kk.picturequizapi.TestFactory.createMockQuizData;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuizPlayServiceTest {

    @Mock
    QuizRepository quizRepository;

    @InjectMocks QuizPlayService playService;

    @Test
    void play_game () throws Exception{
        //given
        given(quizRepository.findById(any()))
                .willReturn(Optional.of(createMockQuizData()));
        //when
        PlayQuizResponse response = playService.playQuiz("123");


        //then
        assertThat(response.getUrl()).isEqualTo("/mock");
        
    
    }    
    
    @Test
    void play_game_exception_id_not_found () throws Exception{
        //given
        given(quizRepository.findById(any()))
                .willReturn(Optional.empty());
        //when //then
        assertThatThrownBy(()-> playService.playQuiz("123"))
                .isInstanceOf(QuizNotFoundByIdException.class);
        

        
    
    }

}