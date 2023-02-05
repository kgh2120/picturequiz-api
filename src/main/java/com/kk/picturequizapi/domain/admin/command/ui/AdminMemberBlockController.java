package com.kk.picturequizapi.domain.admin.command.ui;

import com.kk.picturequizapi.domain.admin.command.application.AdminMemberBlockRequest;
import com.kk.picturequizapi.domain.admin.command.application.AdminMemberBlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminMemberBlockController {

    private final AdminMemberBlockService adminMemberBlockService;

    @PatchMapping("/admin/members")
    public ResponseEntity<Void> blockMember(@RequestBody AdminMemberBlockRequest dto){

        adminMemberBlockService.blockMember(dto);

        return ResponseEntity.ok().build();
    }

}
