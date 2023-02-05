package com.kk.picturequizapi.domain.users.entity;

public enum UserRole {
    ROLE_USER,ROLE_ADMIN;


    public String getRole(){
        return this.name().replace("ROLE_","");
    }
}
