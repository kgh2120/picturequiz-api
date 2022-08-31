package com.kk.picturequizapi.domain.refreshtoken.repository;

import com.kk.picturequizapi.domain.refreshtoken.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshTokenBody(String refreshTokenBody);

    Optional<RefreshToken> findByLoginId(String loginId);
}
