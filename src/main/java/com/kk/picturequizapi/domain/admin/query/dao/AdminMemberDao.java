package com.kk.picturequizapi.domain.admin.query.dao;

import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberPageRequest;
import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberPageResponse;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import java.time.LocalDate;
import java.util.List;

public interface AdminMemberDao {


    CreateCountResponse retrieveCreateCount(LocalDate date);

    AdminMemberPageResponse retrieveMemberAdminPage(AdminMemberPageRequest dto);
}
