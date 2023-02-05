package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.*;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.event.NicknameChangedEvent;
import com.kk.picturequizapi.domain.users.exception.BlockUserLoginException;
import com.kk.picturequizapi.domain.users.exception.DuplicateLoginIdException;
import com.kk.picturequizapi.domain.users.exception.EmailNotFoundException;
import com.kk.picturequizapi.domain.users.exception.InvalidAccessToChangeTemporaryPasswordException;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
import com.kk.picturequizapi.global.event.Events;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service @Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    @Transactional
    @Override
    public SignUpResponseDto signUp(UserAccessRequestDto dto) {

        String encodedPassword = encoder.encode(dto.getPassword());
        Users users = Users.createUserEntity(dto.getLoginId(), encodedPassword);

        Users save = userRepository.save(users);
        return new SignUpResponseDto(save.getId());
    }
    @Override
    public MyInfoResponseDto readMyInfo() {
        return MyInfoResponseDto.createDto(getUser());
    }

    @Override
    public boolean isExistNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    @Override
    public void changeNickname(ChangeNicknameRequestDto dto) {
        Users users = userRepository.findByLoginId(getUser().getLoginId())
                .orElseThrow(LoginDataNotFoundException::new); // 예외 처리를 해야 하나? 없을 수가 없는 거 같은뎀
        users.changeNickname(dto.getNickname());
        Events.raise(new NicknameChangedEvent(users.getId(),dto.getNickname()));
    }

    @Transactional
    @Override
    public void registerEmailAccount(String email) {
        Users users = userRepository.findByLoginId(getUser().getLoginId())
                .orElseThrow(LoginDataNotFoundException::new);
        users.registerEmailAccount(email);
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordDto dto) {
        Users users = userRepository.findByLoginId(getUser().getLoginId())
                .orElseThrow(LoginDataNotFoundException::new);
        users.changePassword(dto.getCurrentPassword(), dto.getNewPassword(), encoder);
    }
    @Transactional
    @Override
    public void deleteAccount() {
        Users users = userRepository.findByLoginId(getUser().getLoginId())
                .orElseThrow(LoginDataNotFoundException::new);
        users.deleteAccount();

    }

    @Override
    public FindLoginIdDto findLoginId(String email) {
        Users users = userRepository.findByAuthEmail(email).orElseThrow(EmailNotFoundException::new);
        return new FindLoginIdDto(users.getLoginId());
    }

    @Override
    public String createTemporaryPassword(String email, String loginId) {
        Users users = userRepository.findByAuthEmailAndLoginId(email, loginId)
                .orElseThrow(InvalidAccessToChangeTemporaryPasswordException::new);
        return users.createTemporaryPassword(encoder);
    }

    @Override
    public void isExistLoginId(String loginId) {
        if(userRepository.existsByLoginId(loginId))
            throw new DuplicateLoginIdException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByLoginId(username)
                .orElseThrow(LoginDataNotFoundException::new);
        isBlockedUser(users);
        return users;
    }

    private void isBlockedUser(Users users) {
        if(users.getBlockedDate()!=null && users.getBlockedDate().isAfter(LocalDate.now()))
            throw new BlockUserLoginException(users.getBlockedDate());
    }

    private Users getUser() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                ;
    }
}
