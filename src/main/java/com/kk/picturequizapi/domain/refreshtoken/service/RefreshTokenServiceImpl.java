package com.kk.picturequizapi.domain.refreshtoken.service;

import com.kk.picturequizapi.domain.refreshtoken.entity.RefreshToken;
import com.kk.picturequizapi.domain.refreshtoken.repository.RefreshTokenRepository;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import com.kk.picturequizapi.global.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService{

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void createToken(String loginId, String refreshTokenBody) {
        RefreshToken token = RefreshToken.createRefreshTokenEntity(refreshTokenBody, loginId);
        refreshTokenRepository.findByLoginId(loginId)
                .ifPresent(refreshTokenRepository::delete);
        refreshTokenRepository.save(token);
    }

    @Override
    public TokenResponseDto refreshTokens(String refreshTokenBody) {

        String loginId = jwtProvider.getUserEmail(refreshTokenBody);
        String accessToken = jwtProvider.createAccessToken(loginId);
        String refreshToken = jwtProvider.createRefreshToken(loginId);

        RefreshToken token = refreshTokenRepository.findByRefreshTokenBody(refreshTokenBody)
                .orElseThrow();
        token.modifyTokenBody(refreshToken);


        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken).build();
    }
}
