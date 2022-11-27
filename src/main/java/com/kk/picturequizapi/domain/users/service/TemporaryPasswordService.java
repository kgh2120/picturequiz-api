package com.kk.picturequizapi.domain.users.service;

public interface TemporaryPasswordService {

    void changePasswordToTemporaryPassword(String email, String loginId);
}
