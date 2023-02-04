package com.kk.picturequizapi.domain.admin.command.application;


import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminCreateService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void createAdminAccount(UserAccessRequestDto dto){
        createAndSaveAdminAccount(dto)
                .initAdminNickname();

    }

    private Users createAndSaveAdminAccount(UserAccessRequestDto dto) {
        Users admin = createAdminEntity(dto);
        userRepository.save(admin);
        return admin;
    }

    private Users createAdminEntity(UserAccessRequestDto dto) {
        String encodedPassword = encoder.encode(dto.getPassword());
        return Users.createAdminAccount(dto.getLoginId(), encodedPassword);
    }

}
