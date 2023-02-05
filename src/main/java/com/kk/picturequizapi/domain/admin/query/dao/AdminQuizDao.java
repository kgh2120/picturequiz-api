package com.kk.picturequizapi.domain.admin.query.dao;

import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import java.time.LocalDate;

public interface AdminQuizDao {


    CreateCountResponse retrieveCreateCount(LocalDate date);
}
