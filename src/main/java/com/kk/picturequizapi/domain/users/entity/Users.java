package com.kk.picturequizapi.domain.users.entity;

import com.kk.picturequizapi.domain.users.exception.ChangePasswordSameException;
import com.kk.picturequizapi.domain.users.exception.PasswordIncorrectException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Users implements UserDetails {

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

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
    public void registerEmailAccount(String email) {
        this.authEmail = email;
    }

    public void deleteAccount() {
        this.loginId = UUID.randomUUID().toString();
        this.authEmail = null;
        this.nickname = null;
    }

    public void changePassword(String currentPassword, String newPassword, PasswordEncoder encoder) {

        if (!encoder.matches(currentPassword, password)) {
            throw new PasswordIncorrectException();
        }
        if (currentPassword.equals(newPassword)) {
            throw new ChangePasswordSameException();
        }
        this.password = encoder.encode(newPassword);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return this.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
