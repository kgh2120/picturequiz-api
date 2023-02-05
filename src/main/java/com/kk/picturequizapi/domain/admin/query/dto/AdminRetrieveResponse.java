package com.kk.picturequizapi.domain.admin.query.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminRetrieveResponse {
    private String loginId;
    private String nickName;
    private LocalDate createdAt;

}
