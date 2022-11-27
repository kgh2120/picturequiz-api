package com.kk.picturequizapi.domain.users.dto;

import lombok.Data;

@Data
public class FindLoginIdDto {

    private String loginId;

    public FindLoginIdDto(String loginId) {
        this.loginId = loginId;
    }
}
