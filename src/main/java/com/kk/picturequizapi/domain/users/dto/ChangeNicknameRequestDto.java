package com.kk.picturequizapi.domain.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data @NoArgsConstructor @AllArgsConstructor
public class ChangeNicknameRequestDto {

    @NotNull
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{4,10}")
    private String nickname;
}
