package com.kk.picturequizapi.domain.quiz.infra;

import com.kk.picturequizapi.domain.quiz.command.domain.Author;
import com.kk.picturequizapi.domain.quiz.command.domain.AuthorService;
import com.kk.picturequizapi.domain.users.entity.UserId;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LogInIdNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {

    private final UserRepository userRepository;

    @Override
    public Author createAuthor(String loginId) {
        Users users = userRepository.findByLoginId(loginId)
                .orElseThrow(LogInIdNotFoundException::new);
        return new Author(UserId.of(users.getId()), users.getNickname());
    }
}
