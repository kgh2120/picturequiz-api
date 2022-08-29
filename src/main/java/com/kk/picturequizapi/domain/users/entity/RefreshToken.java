package com.kk.picturequizapi.domain.users.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity @Getter
public class RefreshToken {

    @Column(name = "refresh_token_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private String refreshTokenBody;

    public static RefreshToken createRefreshTokenEntity(String refreshToken, Users user) {
        RefreshToken token = new RefreshToken();
        token.refreshTokenBody = refreshToken;
        token.user = user;
        return token;
    }
}
