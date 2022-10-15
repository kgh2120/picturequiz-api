package com.kk.picturequizapi.domain.quiz.command.application;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import com.kk.picturequizapi.domain.quiz.command.domain.*;
import com.kk.picturequizapi.domain.tag.query.dao.TagSearchDao;
import com.kk.picturequizapi.domain.tag.query.dto.TagSearch;
import com.kk.picturequizapi.domain.users.entity.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;

import static com.kk.picturequizapi.TestFactory.createUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class QuizCreateServiceTest {
    
    @Mock
    AuthorService authorService;
    
    @Mock
    AnswerService answerService;
    
    @Mock
    QuizRepository quizRepository;
    
    @Mock
    PictureUploadService pictureUploadService;

    
    @Mock
    TagSearchDao tagSearchDao;
    
    @InjectMocks QuizCreateService quizCreateService;
    
    @Test
    void createQuiz () throws Exception{
        //given
        setMockMethod();
        QuizCreateRequest request = new QuizCreateRequest();
        request.setCharacterId(1L);
        request.setTagNames(new ArrayList<>());
        //when
        quizCreateService.createQuiz(new MockMultipartFile("file", new byte[10]), request);
        //then

    }

    private void setMockMethod() throws Exception {
        given(authorService.createAuthor(any()))
                .willReturn(new Author(UserId.of(1L),"작가"));
        given(answerService.createAnswer(any()))
                .willReturn(new Answer(CharacterId.of(1L),"정답"));
        given(quizRepository.nextId())
                .willReturn("123");
        given(pictureUploadService.uploadPicture(any()))
                .willReturn(new Picture("123"));
//        given(tagSearchDao.findByName(any()))
//                .willReturn(Optional.of(new TagSearch("1","hello")));
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        createUser("id","password"),"password",null));

    }
    

}