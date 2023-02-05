package com.kk.picturequizapi.domain.admin.command.ui;


import com.kk.picturequizapi.domain.admin.command.application.AdminAccountCreateService;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminAccountCreateController {

    private final AdminAccountCreateService adminAccountCreateService;

    @PostMapping("/admin")
    public ResponseEntity<Void> createAdmin(@RequestBody UserAccessRequestDto dto){
        adminAccountCreateService.createAdminAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
