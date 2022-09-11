package com.kk.picturequizapi.domain.users.service;

public interface VerificationService {

    void mailSend(String email);

    void verifyCode(String email, String code);
}
