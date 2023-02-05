package com.kk.picturequizapi.domain.admin.command.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminMemberBlockRequest {

    private Long userId;
    private long blockDays;

}
