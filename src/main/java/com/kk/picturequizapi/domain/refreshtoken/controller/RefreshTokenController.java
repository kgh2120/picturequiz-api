package com.kk.picturequizapi.domain.refreshtoken.controller;

import com.kk.picturequizapi.domain.refreshtoken.service.RefreshTokenService;
import com.kk.picturequizapi.domain.users.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<Void> refreshTokens(HttpServletResponse response, @RequestBody Map<String,String> map) {
        String refreshToken = map.get("refreshToken");

        TokenResponseDto dto = refreshTokenService.refreshTokens(refreshToken);

        response.addHeader("accessToken", dto.getAccessToken());
        response.addHeader("refreshToekn",dto.getRefreshToken());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
