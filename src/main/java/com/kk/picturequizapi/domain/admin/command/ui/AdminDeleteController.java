package com.kk.picturequizapi.domain.admin.command.ui;

import com.kk.picturequizapi.domain.admin.command.application.AdminDeleteService;
import com.kk.picturequizapi.domain.users.dto.UserIdRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminDeleteController {

    private final AdminDeleteService adminDeleteService;

    @DeleteMapping("/admin")
    public ResponseEntity<Void> deleteAdminAccount(@RequestBody UserIdRequestDto dto){

        adminDeleteService.deleteAdminAccount(dto.getUserId());
        return ResponseEntity.ok().build();
    }

}
