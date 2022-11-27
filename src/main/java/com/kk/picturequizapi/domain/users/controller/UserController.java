package com.kk.picturequizapi.domain.users.controller;

import com.kk.picturequizapi.domain.users.dto.*;
import com.kk.picturequizapi.domain.users.service.TemporaryPasswordService;
import com.kk.picturequizapi.domain.users.service.UserService;
import com.kk.picturequizapi.domain.users.service.MailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Validated
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final MailSendService mailSendService;
    private final TemporaryPasswordService temporaryPasswordService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Validated UserAccessRequestDto dto) {
        SignUpResponseDto responseDto = userService.signUp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<MyInfoResponseDto> readMyInfo() {
        return ResponseEntity.ok(userService.readMyInfo());
    }

    @GetMapping("/my-profile/nickname")
    public ResponseEntity<Boolean> checkNicknameDuplicate(
            @RequestParam("nickname")
            @NotNull
            @Pattern(regexp = "^[가-힣a-zA-Z0-9]{4,10}") String nickname) {
        boolean result = userService.isExistNickname(nickname);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/my-profile/nickname")
    public ResponseEntity<Void> changeNickname(@RequestBody @Validated ChangeNicknameRequestDto dto) {
        userService.changeNickname(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/my-profile/send-mail")
    public ListenableFuture<ResponseEntity<Void>> sendAuthEmail(@RequestBody @Validated MailRequestDto dto) {
        mailSendService.sendAuthMail(dto.getEmail());
        return new AsyncResult<>(ResponseEntity.noContent().build());
    }

    @PatchMapping("/my-profile/verify-code")
    public ResponseEntity<Void> verifyCode(@RequestBody @Validated CodeVerificationRequestDto dto) {
        mailSendService.verifyCode(dto.getEmail(),dto.getCode());
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/my-profile/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordDto dto) {
        userService.changePassword(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/my-profile")
    public ResponseEntity<Void> deleteAccount() {
        userService.deleteAccount();
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/id")
    public ResponseEntity<FindLoginIdDto> findLoginId(@RequestBody MailRequestDto dto){
        FindLoginIdDto loginIdDto = userService.findLoginId(dto.getEmail());
        return ResponseEntity.ok(loginIdDto);
    }
    @PatchMapping("/user/password")
    public ResponseEntity<Void> changeToTemporaryPassword(@RequestBody TemporaryPasswordDto dto) {
        temporaryPasswordService.changePasswordToTemporaryPassword(dto.getEmail(), dto.getLoginId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
