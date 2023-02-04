package com.kk.picturequizapi.domain.admin.query.ui;

import com.kk.picturequizapi.domain.admin.query.dao.AdminMemberDao;
import com.kk.picturequizapi.domain.admin.query.dto.MemberCreateCountResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminRetrieveMemberController {

    private final AdminMemberDao adminMemberDao;

    @GetMapping("/admin/members")
    public ResponseEntity<List<MemberCreateCountResponse>> retrieveMemberCreateCount(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return ResponseEntity.ok(adminMemberDao.retrieveCreateCount(date));
    }
}
