package com.kk.picturequizapi.domain.users.repository;

import com.kk.picturequizapi.domain.admin.query.dto.AdminRetrieveResponse;
import com.kk.picturequizapi.domain.users.entity.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByLoginId(String loginId);

    boolean existsByNickname(String nickname);

    Optional<Users> findByAuthEmail(String email);

    Optional<Users> findByAuthEmailAndLoginId(String email, String loginId);

    boolean existsByLoginId(String loginId);
    @Query("select new com.kk.picturequizapi.domain.admin.query.dto.AdminRetrieveResponse(u.id, u.loginId, u.nickname, u.createdDate) from Users u"
            + " where u.role = 'ROLE_ADMIN'")
    List<AdminRetrieveResponse> findAllAdminAccount();
}
