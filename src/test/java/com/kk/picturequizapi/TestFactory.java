package com.kk.picturequizapi;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
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

    public static Users createUser(String id, String pwd) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Users user = Users.createUserEntity(id, encoder.encode(pwd));
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);
        return user;
    }


    public static List<CharacterSearch> create5CharacterSearch()throws Exception {
        List<CharacterSearch> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            CharacterSearch characterSearch = new CharacterSearch();
            Field characterId = characterSearch.getClass().getDeclaredField("characterId");
            characterId.setAccessible(true);
            characterId.set(characterSearch, (long) i);

            Field name = characterSearch.getClass().getDeclaredField("name");
            name.setAccessible(true);
            name.set(characterSearch,"이름"+i);

            Field job = characterSearch.getClass().getDeclaredField("job");
            job.setAccessible(true);
            job.set(characterSearch,"학생");
            list.add(characterSearch);
        }
        return list;
    }

    public static CharacterSearch createCharacterSearch() throws  Exception {
        CharacterSearch characterSearch = new CharacterSearch();
        Field characterId = characterSearch.getClass().getDeclaredField("characterId");
        characterId.setAccessible(true);
        characterId.set(characterSearch, (long) 1);

        Field name = characterSearch.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(characterSearch,"이름");

        Field job = characterSearch.getClass().getDeclaredField("job");
        job.setAccessible(true);
        job.set(characterSearch,"학생");
        return characterSearch;
    }

    public static Quiz createMockQuiz() throws Exception {
        List<QuizTag> tag = new ArrayList<>();
        tag.add(new QuizTag(TagId.of("111"),"운동"));
        tag.add(new QuizTag(TagId.of("112"),"운동2"));

        return new Quiz(QuizId.of("123"), new Author(UserId.of(1L),"작가")
        ,new Picture("/mock"), new Answer(CharacterId.of(1L),"정답"), tag);
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
