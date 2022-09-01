package com.kk.picturequizapi.domain.refreshtoken.service;

import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;

public interface RefreshTokenService {

    // 토큰 생성
    void createToken(String loginId, String refreshTokenBody);

    // 토큰 리프래쉬
    TokenResponseDto refreshTokens(String refreshTokenBody);
}
