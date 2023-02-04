package com.kk.picturequizapi.domain.users.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserIdRequestDto {

    @NotNull
    private Long userId;
}
