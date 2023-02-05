package com.kk.picturequizapi;

import com.kk.picturequizapi.domain.quiz.command.application.PlayQuizResponse;
import com.kk.picturequizapi.domain.quiz.command.domain.*;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearch;
import com.kk.picturequizapi.domain.quiz.query.dto.QuizSearchResponse;
import com.kk.picturequizapi.domain.tag.command.domain.TagId;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class TestFactory {

    public static Users createUser(String loginId, String pwd) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Users user = Users.createUserEntity(loginId, encoder.encode(pwd));
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);
        return user;
    }






    public static Quiz createMockQuiz() throws Exception {
        List<QuizTag> tag = new ArrayList<>();
        tag.add(new QuizTag(TagId.of("111"),"운동", "#123123"));
        tag.add(new QuizTag(TagId.of("112"),"운동2","#123123"));

        return new Quiz(QuizId.of("123"), new Author(UserId.of(1L),"작가")
        ,new Picture("/mock"), new Answer("정답"), tag);
    }
    public static Author createMockAuthor() throws Exception {
        return new Author(UserId.of(1L),"nickname");
    }
    public static QuizSearchResponse createMockQuizSearchResponse() throws Exception {

        List<QuizSearch> searches = new ArrayList<>();
        searches.add(new QuizSearch(createMockQuiz()));
        searches.add(new QuizSearch(createMockQuiz()));
        searches.add(new QuizSearch(createMockQuiz()));
        return new QuizSearchResponse(searches,1,true);
    }

    public static PlayQuizResponse createMockPlayQuizResponse() {
        return new PlayQuizResponse("/mock","hello");
    }
}
