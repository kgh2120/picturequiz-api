package com.kk.picturequizapi.domain.users.controller;

import com.kk.picturequizapi.domain.users.dto.*;
import com.kk.picturequizapi.domain.users.service.UserService;
import com.kk.picturequizapi.domain.users.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    public ListenableFuture<ResponseEntity<Void>> sendAuthEmail(@RequestBody @Validated MailSendRequestDto dto) {
        verificationService.mailSend(dto.getEmail());
        return new AsyncResult<>(ResponseEntity.noContent().build());
    }

    @PatchMapping("/my-profile/verify-code")
    public ResponseEntity<Void> verifyCode(@RequestBody @Validated CodeVerificationRequestDto dto) {
        verificationService.verifyCode(dto.getEmail(),dto.getCode());
        return ResponseEntity.noContent().build();
    }
}
