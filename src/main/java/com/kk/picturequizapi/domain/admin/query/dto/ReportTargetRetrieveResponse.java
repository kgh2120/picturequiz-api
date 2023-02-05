package com.kk.picturequizapi.domain.admin.query.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportTargetRetrieveResponse {

    private List<ReportTarget> targets;

    private long currentPage;
    private long lastPage;


}
