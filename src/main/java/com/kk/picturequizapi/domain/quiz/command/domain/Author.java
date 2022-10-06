package com.kk.picturequizapi.domain.quiz.command.domain;

import com.kk.picturequizapi.domain.users.entity.UserId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
@NoArgsConstructor @Getter
public class Author {

    @Embedded
    private UserId userid;

    private String nickname;

    public Author(UserId userid, String nickname) {
        this.userid = userid;
        this.nickname = nickname;
    }

}
