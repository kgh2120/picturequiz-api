package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.*;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.ChangePasswordSameException;
import com.kk.picturequizapi.domain.users.exception.EmailNotFoundException;
import com.kk.picturequizapi.domain.users.exception.PasswordIncorrectException;
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
    
    @Test
    void changePassword () throws Exception{
        //given
        Users users = Users.createUserEntity("hello", encoder.encode("password"));
        //when
        ChangePasswordDto dto = new ChangePasswordDto();
        dto.setCurrentPassword("password");
        dto.setNewPassword("password1");

       users.changePassword(dto.getCurrentPassword(), dto.getNewPassword(), encoder);

        SoftAssertions.assertSoftly(sa -> {
            assertThat(encoder.matches("password",users.getPassword())).isFalse();
            assertThat(encoder.matches("password1",users.getPassword())).isTrue();
        });
    }
    
    @Test
    void changePassword_ex_passwordIncorrect () throws Exception{
        //given
        Users users = Users.createUserEntity("hello", encoder.encode("password"));
        //when
        ChangePasswordDto dto = new ChangePasswordDto();
        dto.setCurrentPassword("password123");
        dto.setNewPassword("password1");

        assertThatThrownBy(()
                -> users.changePassword(dto.getCurrentPassword(), dto.getNewPassword(), encoder))
                .isInstanceOf(PasswordIncorrectException.class);
    }

    @Test
    void changePassword_ex_samePassword () throws Exception{
        //given
        Users users = Users.createUserEntity("hello", encoder.encode("password"));
        //when
        ChangePasswordDto dto = new ChangePasswordDto();
        dto.setCurrentPassword("password");
        dto.setNewPassword("password");

        assertThatThrownBy(()
                -> users.changePassword(dto.getCurrentPassword(), dto.getNewPassword(), encoder))
                .isInstanceOf(ChangePasswordSameException.class);
    }
    
    @Test
    void deleteAccount () throws Exception{
        //given
        String id = "hello";
        Users users = Users.createUserEntity(id, encoder.encode("password"));
        users.registerEmailAccount("abcd@gamil.com");
        //when
        users.deleteAccount();


        SoftAssertions.assertSoftly(sa -> {
            assertThat(users.getNickname()).isNull();
            assertThat(users.getAuthEmail()).isNull();
            assertThat(users.getLoginId()).isNotEqualTo(id);
        });
    }

    @Test
    void findLoginIdTest () throws Exception{
        //given
        String email = "email@gmail.com";
        given(userRepository.findByAuthEmail(anyString()))
                .willReturn(Optional.of(createUser("loginId","password")));

        //when
        FindLoginIdDto finded = userService.findLoginId(email);

        //then
        assertThat(finded.getLoginId()).isEqualTo("loginId");
    }

    @Test
    void findLoginIdTest_Email_Not_Found () throws Exception{
        //given
        String email = "email@gmail.com";
        given(userRepository.findByAuthEmail(anyString()))
                .willThrow(new EmailNotFoundException());
        //when // then
        assertThatThrownBy(() ->userService.findLoginId(email))
                .isInstanceOf(EmailNotFoundException.class);
    }
}