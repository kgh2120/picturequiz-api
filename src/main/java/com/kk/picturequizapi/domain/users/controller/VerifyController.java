package com.kk.picturequizapi.domain.users.controller;

import com.kk.picturequizapi.domain.users.service.VerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class VerifyController {

    private final VerificationService verificationService;

//    @PostMapping("/")

}
