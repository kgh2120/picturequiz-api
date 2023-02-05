package com.kk.picturequizapi.domain.users.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ChangePasswordDto {

    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&()-])(?=.*[a-zA-Z]).{8,20}$")
    private String currentPassword;

    @NotNull
    @Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&()-])(?=.*[a-zA-Z]).{8,20}$")
    private String newPassword;
}
