package com.kk.picturequizapi.domain.users.dto;

import com.kk.picturequizapi.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MyInfoResponseDto {

    private String loginId;
    private String nickname;
    private String authEmail;

    public static MyInfoResponseDto createDto(Users users) {
        return MyInfoResponseDto.builder()
                .loginId(users.getLoginId())
                .nickname(users.getNickname())
                .authEmail(users.getAuthEmail())
                .build();
    }
}
