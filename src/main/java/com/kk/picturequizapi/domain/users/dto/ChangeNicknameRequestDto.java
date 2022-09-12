package com.kk.picturequizapi.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ChangeNicknameRequestDto {
    private String nickname;
}
