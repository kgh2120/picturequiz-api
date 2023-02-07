package com.kk.picturequizapi.domain.admin.query.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminMemberPageResponse {

    private List<AdminMemberInfo> members;
    private long currentPage;
    private long nextPage;
    private long lastPage;
}
