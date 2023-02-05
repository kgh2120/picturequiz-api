package com.kk.picturequizapi.domain.admin.command.application;

import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.UserIdNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminMemberBlockService {

    private final UserRepository userRepository;

    public void blockMember(AdminMemberBlockRequest dto) {
        getUsers(dto).block(dto.getBlockDays());
    }

    private Users getUsers(AdminMemberBlockRequest dto) {
        return userRepository.findById(dto.getUserId())
                .orElseThrow(UserIdNotFoundException::new);
    }
}
