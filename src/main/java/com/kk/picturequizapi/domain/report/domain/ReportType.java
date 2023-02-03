package com.kk.picturequizapi.domain.report.domain;

public enum ReportType {
    UNRELATED_PICTURE(TargetType.QUIZ), INAPPROPRIATE_PICTURE(TargetType.QUIZ), INAPPROPRIATE_TITLE(
            TargetType.QUIZ), INAPPROPRIATE_TAG(TargetType.QUIZ), ETC(TargetType.QUIZ),
    SPAM_COMMENT(TargetType.COMMENT), PORN_COMMENT(TargetType.COMMENT), CHILD_ABUSE_COMMENT(
            TargetType.COMMENT), VIOLATION_COMMENT(TargetType.COMMENT), TERROR_COMMENT(
            TargetType.COMMENT), SUICIDE_COMMENT(TargetType.COMMENT),
    WRONG_INFO_COMMENT(TargetType.COMMENT);
    private TargetType targetType;


    ReportType(TargetType targetType) {
        this.targetType = targetType;
    }

    public boolean validateTargetType(TargetType targetType) {
        return this.targetType.equals(targetType);
    }
}
