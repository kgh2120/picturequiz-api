package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.MyInfoResponseDto;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    void signUp() throws Exception {
        //given
        String id = "test";
        String pwd = "password";
        given(passwordEncoder.encode(pwd))
                .willReturn(encoder.encode(pwd));
        given(userRepository.save(any()))
                .willReturn(createUser(id, pwd));

        UserAccessRequestDto dto = new UserAccessRequestDto();
        dto.setLoginId(id);
        dto.setPassword(pwd);
        //when

        SignUpResponseDto responseDto = userService.signUp(dto);
        //then
        assertThat(responseDto.getId()).isSameAs(1L);
    }
    
    @Test
    void readMyInfo () throws Exception{
        //given
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        createUser("tester","password"),"password",null));
        //when
        MyInfoResponseDto dto = userService.readMyInfo();
        //then
        SoftAssertions.assertSoftly(sa -> {
            assertThat(dto.getLoginId()).isEqualTo("tester");
            assertThat(dto.getNickname()).isNull();
            assertThat(dto.getAuthEmail()).isNull();
        });
    }

    private Users createUser(String id, String pwd) throws Exception {
        Users user = Users.createUserEntity(id, encoder.encode(pwd));
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);
        return user;
    }

}