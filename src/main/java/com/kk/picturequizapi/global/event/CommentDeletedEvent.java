package com.kk.picturequizapi.global.event;

import lombok.Getter;

@Getter
public class CommentDeletedEvent extends Event{

    private final String commentId;

    public CommentDeletedEvent(String commentId) {
        this.commentId = commentId;
    }
}
