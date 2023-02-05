package com.kk.picturequizapi.domain.comment.command.application;

import com.kk.picturequizapi.domain.users.event.NicknameChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class NicknameChangedEventHandler {

    private final AuthorChangeService authorChangeService;

    @EventListener(NicknameChangedEvent.class)
    public void handle(NicknameChangedEvent event){
        authorChangeService.changeAuthorNickname(event.getUserId(), event.getNickname());
    }
}
