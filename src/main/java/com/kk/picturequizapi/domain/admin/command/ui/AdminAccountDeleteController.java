package com.kk.picturequizapi.domain.admin.command.ui;

import com.kk.picturequizapi.domain.admin.command.application.AdminAccountDeleteService;
import com.kk.picturequizapi.domain.users.dto.UserIdRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminAccountDeleteController {

    private final AdminAccountDeleteService adminAccountDeleteService;

    @DeleteMapping("/admin")
    public ResponseEntity<Void> deleteAdminAccount(@RequestBody UserIdRequestDto dto){

        adminAccountDeleteService.deleteAdminAccount(dto.getUserId());
        return ResponseEntity.ok().build();
    }

}
