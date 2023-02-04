package com.kk.picturequizapi.domain.admin.query.ui;

import com.kk.picturequizapi.domain.admin.query.dto.AdminRetrieveResponse;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminRetrieveController {

    private final UserRepository userRepository;

    @GetMapping("/admin")
    public ResponseEntity<List<AdminRetrieveResponse>> retrieveAdmin(){
        return ResponseEntity.ok(userRepository.findAllAdminAccount());
    }

}
