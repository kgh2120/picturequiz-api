package com.kk.picturequizapi.domain.users.entity;

import com.kk.picturequizapi.domain.users.exception.ChangePasswordSameException;
import com.kk.picturequizapi.domain.users.exception.PasswordIncorrectException;
import com.kk.picturequizapi.global.jpa.BaseEntity;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class Users extends BaseEntity implements UserDetails {

    @Column(name = "user_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
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
        user.role = UserRole.ROLE_USER;
        return user;
    }

    public static Users createAdminAccount(String loginId, String encrytedPassword) {
        Users user = new Users();
        user.loginId = loginId;
        user.password = encrytedPassword;
        user.role = UserRole.ROLE_ADMIN;
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

    public String createTemporaryPassword(PasswordEncoder encoder){
        String temporaryPassword = UUID.randomUUID().toString().substring(0, 12);
        this. password = encoder.encode(temporaryPassword);
        return temporaryPassword;
    }

    public void initAdminNickname(){
        this.nickname = "관리자" + id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
