package com.kk.picturequizapi.domain.users.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UsersTest {


    @Test
    @DisplayName("임시 비밀번호 테스트")
    void temporaryPasswordTest () throws Exception{
        //given
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Users users = new Users();
        //when

        String temporaryPassword = users.createTemporaryPassword(encoder);
        //then

        assertThat(encoder.matches(temporaryPassword, users.getPassword())).isTrue();


    }
}