package com.kk.picturequizapi.domain.admin.command.application;

import com.kk.picturequizapi.domain.admin.exception.OnlyAdminAccountDeleteException;
import com.kk.picturequizapi.domain.users.entity.UserRole;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.UserIdNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminAccountDeleteService {

    private final UserRepository userRepository;

    public void deleteAdminAccount(Long adminId){
        validateAdminId(adminId);
        delete(adminId);

    }

    private void delete(Long adminId) {
        userRepository.deleteById(adminId);
    }
    private void validateAdminId(Long adminId) {
        Users users = userRepository.findById(adminId)
                .orElseThrow(UserIdNotFoundException::new);

        if(users.getRole().equals(UserRole.ROLE_USER))
            throw new OnlyAdminAccountDeleteException();
    }

}
