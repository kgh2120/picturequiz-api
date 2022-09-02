package com.kk.picturequizapi.domain.users.controller;

import com.kk.picturequizapi.domain.users.dto.ChangeNicknameRequestDto;
import com.kk.picturequizapi.domain.users.dto.MyInfoResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.service.MailService;
import com.kk.picturequizapi.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final MailService mailService;

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
    public ResponseEntity<String> sendAuthEmail(@RequestBody Map<String,String> dto) {
        mailService.mailSend(dto.get("email"));
        return ResponseEntity.ok("잘 갔다네?");
    }
}
