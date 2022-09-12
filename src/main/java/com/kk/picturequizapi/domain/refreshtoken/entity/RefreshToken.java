package com.kk.picturequizapi.domain.refreshtoken.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Getter
public class RefreshToken {

    @Column(name = "refresh_token_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String refreshTokenBody;

    public static RefreshToken createRefreshTokenEntity(String refreshToken, String loginId) {
        RefreshToken token = new RefreshToken();
        token.refreshTokenBody = refreshToken;
        token.loginId = loginId;
        return token;
    }

    public void modifyTokenBody(String refreshTokenBody) {
        this.refreshTokenBody = refreshTokenBody;
    }
}
