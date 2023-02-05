package com.kk.picturequizapi.domain.comment.command.application;

import com.kk.picturequizapi.global.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDeletedEvent extends Event {

    private String quizId;



}
