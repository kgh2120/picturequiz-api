package com.kk.picturequizapi.domain.users.event;

import com.kk.picturequizapi.global.event.Event;
import lombok.Getter;

@Getter
public class NicknameChangedEvent extends Event {

    private long userId;
    private String nickname;

    public NicknameChangedEvent(long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }
}
