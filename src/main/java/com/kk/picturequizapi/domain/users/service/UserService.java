package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.LoginResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;

public interface UserService {

    // 회원 가입 기능 아이디와 비밀번호를 받는 DTO 객체
    SignUpResponseDto signUp(UserAccessRequestDto dto);


    // 로그인 기능
    LoginResponseDto login(UserAccessRequestDto dto);


}
