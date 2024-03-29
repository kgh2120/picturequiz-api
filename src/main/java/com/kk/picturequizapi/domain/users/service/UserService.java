package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    // 회원 가입 기능 아이디와 비밀번호를 받는 DTO 객체
    SignUpResponseDto signUp(UserAccessRequestDto dto);


    // 내 정보 조회하기 기능
    MyInfoResponseDto readMyInfo();

    // 닉네임이 존재하는지 체크 하는 기능
    boolean isExistNickname(String nickname);

    void changeNickname(ChangeNicknameRequestDto dto);
    void registerEmailAccount(String email);

    void changePassword(ChangePasswordDto dto);

    void deleteAccount();

    FindLoginIdDto findLoginId(String email);

    String createTemporaryPassword(String email, String loginId);

    void isExistLoginId(String loginId);
}
