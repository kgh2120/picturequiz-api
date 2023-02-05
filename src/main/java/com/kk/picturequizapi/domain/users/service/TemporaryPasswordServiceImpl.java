package com.kk.picturequizapi.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TemporaryPasswordServiceImpl implements TemporaryPasswordService{

    private final UserService userService;
    private final MailSendService mailSendService;

    @Override
    public void changePasswordToTemporaryPassword(String email, String loginId) {
        String temporaryPassword = userService.createTemporaryPassword(email, loginId);
        mailSendService.sendTemporaryPasswordMail(email, temporaryPassword);

    }
}
