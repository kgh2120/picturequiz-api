package com.kk.picturequizapi.domain.admin.query.dto;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateCountResponse {

    private Map<LocalDate, Long> createCount;


    public CreateCountResponse(LocalDate date, List<CreateCount> dtos) {
        createCount = new LinkedHashMap<>();
        for (long i = 6; i >= 0; i--) {
            createCount.put(date.minusDays(i), 0L);
        }
        for (CreateCount dto : dtos) {
            createCount.put(dto.getCreatedDate(),dto.getCount());
        }
    }



}
