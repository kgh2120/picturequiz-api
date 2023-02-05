package com.kk.picturequizapi.domain.admin.query.ui;

import com.kk.picturequizapi.domain.admin.query.dao.AdminCommentDao;
import com.kk.picturequizapi.domain.admin.query.dao.AdminMemberDao;
import com.kk.picturequizapi.domain.admin.query.dao.AdminQuizDao;
import com.kk.picturequizapi.domain.admin.query.dao.AdminReportDao;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCount;
import com.kk.picturequizapi.domain.admin.query.dto.CreateCountResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminCreateCountController {

    private final AdminMemberDao adminMemberDao;
    private final AdminQuizDao adminQuizDao;
    private final AdminCommentDao adminCommentDao;

    private final AdminReportDao adminReportDao;


    @GetMapping("/admin/members")
    public ResponseEntity<CreateCountResponse> retrieveMemberCreateCount(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(adminMemberDao.retrieveCreateCount(date));
    }

    @GetMapping("/admin/quiz")
    public ResponseEntity<CreateCountResponse> retrieveQuizCreateCount(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(adminQuizDao.retrieveCreateCount(date));
    }

    @GetMapping("/admin/comments")
    public ResponseEntity<CreateCountResponse> retrieveCommentCreateCount(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(adminCommentDao.retrieveCreateCount(date));
    }

    @GetMapping("/admin/reports")
    public ResponseEntity<CreateCountResponse> retrieveReportCreateCount(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return ResponseEntity.ok(adminReportDao.retrieveCreateCount(date));
    }

}
