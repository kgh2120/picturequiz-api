package com.kk.picturequizapi.global.constants;

public class RegExpress {
    public final static String LOGIN_ID = "^[A-Za-z0-9]{6,12}";
    public final static String PASSWORD = "^(?=.*\\d)(?=.*[~`!@#$%\\^&()-])(?=.*[a-zA-Z]).{8,20}$";
}
