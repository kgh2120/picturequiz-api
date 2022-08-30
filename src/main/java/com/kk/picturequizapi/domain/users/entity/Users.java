package com.kk.picturequizapi.domain.users.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Users {

    @Column(name = "user_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    private String nickname;

    private String authEmail;

    @Enumerated(EnumType.STRING)
    private UserRole role;



    public static Users createUserEntity(String loginId, String encrytedPassword) {
        Users user = new Users();
        user.loginId = loginId;
        user.password = encrytedPassword;
        user.role = UserRole.USER;
        return user;
    }



}
