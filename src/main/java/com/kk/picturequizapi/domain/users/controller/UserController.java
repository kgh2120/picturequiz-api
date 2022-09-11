package com.kk.picturequizapi.domain.users.controller;

import com.kk.picturequizapi.domain.users.dto.*;
import com.kk.picturequizapi.domain.users.service.VerificationService;
import com.kk.picturequizapi.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final VerificationService verificationService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody UserAccessRequestDto dto) {
        SignUpResponseDto responseDto = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<MyInfoResponseDto> readMyInfo() {
        return ResponseEntity.ok(userService.readMyInfo());
    }

    @GetMapping("/my-profile/nickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@RequestParam("nickname") String nickname) {
        boolean result = userService.isExistNickname(nickname);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/my-profile/nickname")
    public ResponseEntity<Void> changeNickname(@RequestBody ChangeNicknameRequestDto dto) {
        userService.changeNickname(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/my-profile/send-mail")
    public ResponseEntity<String> sendAuthEmail(@RequestBody @Validated MailSendRequestDto dto) {
        verificationService.mailSend(dto.getEmail());
        return ResponseEntity.ok("잘 갔다네?");
    }

    @PostMapping("/my-profile/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody @Validated CodeVerificationRequestDto dto) {
        verificationService.verifyCode(dto.getEmail(),dto.getCode());
        return ResponseEntity.ok("인증 성공");
    }
}
