package com.kk.picturequizapi.domain.admin.query.ui;

import com.kk.picturequizapi.domain.admin.query.dao.AdminMemberDao;
import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberPageRequest;
import com.kk.picturequizapi.domain.admin.query.dto.AdminMemberPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminRetrieveMemberController {

    private final AdminMemberDao adminMemberDao;


    @PostMapping("/admin/members")
    public ResponseEntity<AdminMemberPageResponse> retrieveMemberAdminPage(@RequestBody AdminMemberPageRequest dto){

        return ResponseEntity.ok(adminMemberDao.retrieveMemberAdminPage(dto));

    }
}
