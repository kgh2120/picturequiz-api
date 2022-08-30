package com.kk.picturequizapi.domain.users.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class UserAccessRequestDto {

    private String loginId;
    private String password;

    @Builder
    public UserAccessRequestDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
