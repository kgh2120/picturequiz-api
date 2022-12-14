package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.*;
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

import static com.kk.picturequizapi.TestFactory.createUser;
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
    String loginId = "tester";

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
        setSecurity();
        //when
        MyInfoResponseDto dto = userService.readMyInfo();
        //then
        SoftAssertions.assertSoftly(sa -> {
            assertThat(dto.getLoginId()).isEqualTo("tester");
            assertThat(dto.getNickname()).isNull();
            assertThat(dto.getAuthEmail()).isNull();
        });
    }

    private void setSecurity() throws Exception {

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        createUser(loginId,"password"),"password",null));
    }


    @Test
    void isExistNickname_TRUE () throws Exception{
        //given
        given(userRepository.existsByNickname(any()))
                .willReturn(true);
        //when
        boolean result = userService.isExistNickname("nickname");

        //then
        assertThat(result).isTrue();
    }

    @Test
    void isExistNickname_FALSE () throws Exception{
        //given
        given(userRepository.existsByNickname(any()))
                .willReturn(false);
        //when
        boolean result = userService.isExistNickname("nickname");

        //then
        assertThat(result).isFalse();
    }
    
    @Test
    void changeNickname () throws Exception{
        //given
        setSecurity();
        given(userRepository.findByLoginId(any()))
                .willReturn( Optional.of((Users)SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal()));
        String nickname = "nickname";
        ChangeNicknameRequestDto dto = new ChangeNicknameRequestDto(nickname);
        //when
        userService.changeNickname(dto);
        Users users = userRepository.findByLoginId(loginId).get();
        //then
        assertThat(users.getNickname()).isEqualTo(nickname);
        
    
    }

//    private Users createUser(String id, String pwd) throws Exception {
//        Users user = Users.createUserEntity(id, encoder.encode(pwd));
//        Field idField = user.getClass().getDeclaredField("id");
//        idField.setAccessible(true);
//        idField.set(user, 1L);
//        return user;
//    }

}