package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import com.kk.picturequizapi.domain.character.query.application.CharacterSearchService;
import com.kk.picturequizapi.domain.quiz.command.domain.Answer;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.kk.picturequizapi.TestFactory.createCharacterSearch;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    CharacterSearchService cs;

    @InjectMocks AnswerServiceImpl answerService;

    @Test
    void createAnswer () throws Exception{
        //given
        given(cs.findCharacterById(any()))
                .willReturn(createCharacterSearch());
        //when
        Answer answer = answerService.createAnswer(1L);
        //then

        SoftAssertions.assertSoftly(sa -> {
            assertThat(answer.getCharacterId())
                    .isEqualTo(CharacterId.of(1L));
            assertThat(answer.getName())
                    .isEqualTo("이름");
        });
    }

}