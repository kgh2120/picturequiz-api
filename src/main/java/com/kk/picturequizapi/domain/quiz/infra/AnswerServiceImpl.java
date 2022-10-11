package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.character.command.domain.CharacterId;
import com.kk.picturequizapi.domain.character.query.application.CharacterSearchService;
import com.kk.picturequizapi.domain.character.query.dto.CharacterSearch;
import com.kk.picturequizapi.domain.quiz.command.domain.Answer;
import com.kk.picturequizapi.domain.quiz.command.domain.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {

    private final CharacterSearchService characterSearchService;

    @Override
    public Answer createAnswer(Long answerId) {
        CharacterSearch cs = characterSearchService.findCharacterById(answerId);
        return new Answer(CharacterId.of(cs.getCharacterId()),cs.getName());
    }
}
