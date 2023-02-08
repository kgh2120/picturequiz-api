package com.kk.picturequizapi.domain.report.infra;

import com.kk.picturequizapi.domain.comment.command.domain.CommentId;
import com.kk.picturequizapi.domain.comment.command.domain.CommentRepository;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizId;
import com.kk.picturequizapi.domain.quiz.command.domain.QuizRepository;
import com.kk.picturequizapi.domain.report.domain.TargetId;
import com.kk.picturequizapi.domain.report.domain.TargetType;
import com.kk.picturequizapi.domain.report.domain.TargetValidateService;
import com.kk.picturequizapi.domain.report.exception.InvalidTargetIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TargetValidateServiceImpl implements TargetValidateService {

    private final QuizRepository quizRepository;
    private final CommentRepository commentRepository;

    @Override
    public void validateTargetId(TargetId targetId, TargetType type) {
        if(!isTargetIdExist(targetId,type))
            throw new InvalidTargetIdException();
        commentRepository.findById(CommentId.of(targetId.getId()))
                .orElseThrow().validateAlreadyDeleted();
    }

    private boolean isTargetIdExist(TargetId targetId, TargetType type){
        if(type.equals(TargetType.COMMENT))
           return commentRepository.existsById(CommentId.of(targetId.getId()));
        return quizRepository.existsById(QuizId.of(targetId.getId()));
    }
}
