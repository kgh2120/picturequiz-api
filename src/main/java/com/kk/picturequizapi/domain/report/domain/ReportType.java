package com.kk.picturequizapi.domain.report.domain;

public enum ReportType {
    UNRELATED_PICTURE(TargetType.QUIZ, "관련 없는 사진"),
    INAPPROPRIATE_PICTURE(TargetType.QUIZ, "부적절한 사진"),
    INAPPROPRIATE_TITLE(TargetType.QUIZ, "부적절한 제목"),
    INAPPROPRIATE_TAG(TargetType.QUIZ, "부적절한 태그"),
    ETC(TargetType.QUIZ, "기타"),
    SPAM_COMMENT(TargetType.COMMENT, "원치 않는 상업성 콘텐츠 또는 스팸"),
    PORN_COMMENT(TargetType.COMMENT, "포르노 또는 음란물"),
    CHILD_ABUSE_COMMENT(TargetType.COMMENT, "아동 학대"),
    VIOLATION_COMMENT(TargetType.COMMENT, "고룁힘 또는 폭력"),
    TERROR_COMMENT(TargetType.COMMENT, "테러 조장"),
    SUICIDE_COMMENT(TargetType.COMMENT, "자살 또는 자해"),
    WRONG_INFO_COMMENT(TargetType.COMMENT, "잘못된 정보");
    private TargetType targetType;

    private String kor;

    ReportType(TargetType targetType, String kor) {
        this.targetType = targetType;
        this.kor = kor;
    }

    public boolean validateTargetType(TargetType targetType) {
        return this.targetType.equals(targetType);
    }

    public String getKor(){
        return this.kor;
    }
}
