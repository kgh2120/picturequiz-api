package com.kk.picturequizapi.domain.admin.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class AdminMemberInfo {

    private long memberId;
    private String nickname;
    private String mail;
    private LocalDate createdDate;
    private long nOfQuiz;
    private long nOfComment;

    @QueryProjection
    public AdminMemberInfo(long memberId, String nickname, String mail, LocalDate createdDate, long nOfQuiz, long nOfComment) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.mail = mail;
        this.createdDate = createdDate;
        this.nOfQuiz = nOfQuiz;
        this.nOfComment = nOfComment;
    }
    public void injectNumOfQuizAndComment(long nOfQuiz, long nOfComment){

    }
}
