package com.kk.picturequizapi.domain.report.domain;

import lombok.Getter;

@Getter
public enum TargetType {
    QUIZ("퀴즈"),COMMENT("댓글");

    private final String kor;
    TargetType(String kor) {
        this.kor = kor;
    }


}
