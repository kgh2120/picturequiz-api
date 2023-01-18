package com.kk.picturequizapi.domain.report.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable @AllArgsConstructor @NoArgsConstructor
public class Reporter {

    @Embedded
    private UserId userId;

    private String nickname;


    public static Reporter of(UserId userId, String nickname) {
        return new Reporter(userId,nickname);
    }

    public UserId getUserId() {
        return userId;
    }
}
