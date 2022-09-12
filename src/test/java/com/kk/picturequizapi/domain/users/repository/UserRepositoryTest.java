package com.kk.picturequizapi.domain.users.repository;

import com.kk.picturequizapi.domain.users.entity.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    
    @Test
    void findByLoginId () throws Exception{
        //given
        String loginid = "loginid";
        Users users = Users.createUserEntity(loginid, "password");
        Long id = userRepository.save(users).getId();
        //when
        Users find = userRepository.findByLoginId(loginid).get();
        //then
        assertThat(id).isSameAs(find.getId());
    }
    
    @Test
    void existsByNickname_TRUE  () throws Exception{
        //given
        String loginid = "loginid";
        Users users = Users.createUserEntity(loginid, "password");
        String nickname = "nickname";
        users.changeNickname(nickname);
        userRepository.save(users);

        //when
        boolean result = userRepository.existsByNickname(nickname);

        //then
        assertThat(result).isTrue();
    }
    @Test
    void existsByNickname_FALSE  () throws Exception{
        //given
        String loginid = "loginid";
        Users users = Users.createUserEntity(loginid, "password");
        String nickname = "nickname";
        users.changeNickname(nickname);
        userRepository.save(users);

        //when
        boolean result = userRepository.existsByNickname("nickname2");

        //then
        assertThat(result).isFalse();
    }

}