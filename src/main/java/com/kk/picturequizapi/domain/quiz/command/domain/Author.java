package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor @Getter
public class Author {

    @Embedded
    private UserId userId;

    private String nickname;

    public Author(UserId userid, String nickname) {
        this.userId = userid;
        this.nickname = nickname;
    }

}
