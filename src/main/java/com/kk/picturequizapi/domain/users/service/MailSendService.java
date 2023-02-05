package com.kk.picturequizapi.domain.users.service;

public interface MailSendService {

    void sendAuthMail(String email);

    void verifyCode(String email, String code);

    void sendTemporaryPasswordMail(String email, String temporaryPassword);


}
