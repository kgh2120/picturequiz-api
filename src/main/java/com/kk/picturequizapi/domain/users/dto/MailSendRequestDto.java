package com.kk.picturequizapi.domain.users.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MailSendRequestDto {
    @NotBlank
    @Email
    private String email;
}
