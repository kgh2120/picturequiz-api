package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.LoginResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;
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
    void login() throws Exception {
        //given
        String id = "test";
        String pwd = "password";
        given(passwordEncoder.matches(any(),any()))
                .willReturn(encoder.matches(pwd,createUser(id,pwd).getPassword()));
        given(userRepository.findByLoginId(id))
                .willReturn(Optional.of(createUser(id, pwd)));
        UserAccessRequestDto dto = new UserAccessRequestDto();
        dto.setPassword(pwd);
        dto.setLoginId(id);
        //when

        LoginResponseDto responseDto = userService.login(dto);

        //then
        assertThat(responseDto).isNotNull();
    }

    @Test
    void login_exception_idNotFound () throws Exception{
        //given
        String id = "test";
        String pwd = "password";
        given(userRepository.findByLoginId(any()))
                .willThrow(LoginDataNotFoundException.class);
        UserAccessRequestDto dto = new UserAccessRequestDto();
        dto.setPassword(pwd);
        dto.setLoginId(id);
        //when then
        assertThatThrownBy(() -> userService.login(dto))
                .isInstanceOf(LoginDataNotFoundException.class);
    }
    
    @Test
    void login_exception_passwordUnMatched () throws Exception{
        //given
        String id = "test";
        String pwd = "password";
        given(userRepository.findByLoginId(any()))
                .willReturn(Optional.of(createUser(id, pwd)));
        given(passwordEncoder.matches(any(),any()))
                .willReturn(encoder.matches("unMatchedPassword",createUser(id,pwd).getPassword()));
        UserAccessRequestDto dto = new UserAccessRequestDto();
        dto.setPassword(pwd);
        dto.setLoginId(id);
        //when then
        assertThatThrownBy(() -> userService.login(dto))
                .isInstanceOf(LoginDataNotFoundException.class);

    }

    private Users createUser(String id, String pwd) throws Exception {
        Users user = Users.createUserEntity(id, encoder.encode(pwd));
        Field idField = user.getClass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);
        return user;
    }

}