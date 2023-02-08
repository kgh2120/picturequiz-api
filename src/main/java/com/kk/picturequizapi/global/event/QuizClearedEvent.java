package com.kk.picturequizapi.global.event;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class QuizClearedEvent extends Event{

    private final List<String> ids;

}
