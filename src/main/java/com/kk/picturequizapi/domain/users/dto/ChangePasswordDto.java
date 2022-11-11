package com.kk.picturequizapi.domain.users.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String currentPassword;
    private String newPassword;
}
