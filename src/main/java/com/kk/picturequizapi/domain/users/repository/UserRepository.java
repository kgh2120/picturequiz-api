package com.kk.picturequizapi.domain.users.repository;

import com.kk.picturequizapi.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLoginId(String loginId);

    boolean existsByNickname(String nickname);
}
