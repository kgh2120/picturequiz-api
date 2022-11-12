package com.kk.picturequizapi.domain.users.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data @NoArgsConstructor
public class UserAccessRequestDto {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}")
    private String loginId;

    @NotNull
    @Pattern(regexp = "^(?=.\\d)(?=.[~`!@#$%\\^&()-])(?=.*[a-zA-Z]).{8,20}$")
    private String password;

    @Builder
    public UserAccessRequestDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
