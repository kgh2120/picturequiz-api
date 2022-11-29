package com.kk.picturequizapi.global.security;

import com.kk.picturequizapi.global.constants.RegExpress;

import java.util.regex.Pattern;

public final class LoginValidator {

    private static boolean validateId(String loginId){
        return loginId != null && Pattern.matches(RegExpress.LOGIN_ID,loginId);
    }
    private static boolean validatePassword(String password){
        return password != null && Pattern.matches(RegExpress.PASSWORD,password);
    }

    public static boolean validateLogin(String loginId, String password){
        return validateId(loginId) && validatePassword(password);
    }
}
