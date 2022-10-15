package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.quiz.command.domain.Author;
import com.kk.picturequizapi.domain.users.exception.LogInIdNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.kk.picturequizapi.TestFactory.createUser;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    
    @Mock
    UserRepository userRepository;
    
    @InjectMocks AuthorServiceImpl authorService;
    
    @Test
    void createAuthor () throws Exception{
        //given
        given(userRepository.findByLoginId(any()))
                .willReturn(Optional.of(createUser("testId", "testPw")));
        //when
        Author author = authorService.createAuthor("testId");
        //then
        SoftAssertions.assertSoftly(sa -> {
            assertThat(author.getUserId().getId())
                    .isEqualTo(1L);
            assertThat(author.getNickname()).isNull();
        });
    }

    @Test
    void createAuthor_LoginIdException () throws Exception{
        //given
        given(userRepository.findByLoginId(any()))
                .willReturn(Optional.empty());
        //when //then
       assertThatThrownBy(()->authorService.createAuthor("testId"))
               .isInstanceOf(LogInIdNotFoundException.class);


    }

}