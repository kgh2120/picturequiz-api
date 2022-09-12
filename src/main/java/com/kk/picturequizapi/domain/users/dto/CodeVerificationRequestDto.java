package com.kk.picturequizapi.domain.users.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CodeVerificationRequestDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String code;
}
