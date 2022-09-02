package com.kk.picturequizapi.domain.users.service;

import com.kk.picturequizapi.domain.users.dto.ChangeNicknameRequestDto;
import com.kk.picturequizapi.domain.users.dto.MyInfoResponseDto;
import com.kk.picturequizapi.domain.users.dto.SignUpResponseDto;
import com.kk.picturequizapi.domain.users.dto.UserAccessRequestDto;
import com.kk.picturequizapi.domain.users.entity.Users;
import com.kk.picturequizapi.domain.users.exception.LoginDataNotFoundException;
import com.kk.picturequizapi.domain.users.repository.UserRepository;
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
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLoginId(username)
                .orElseThrow(LoginDataNotFoundException::new);
    }

    private Users getUser() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                ;
    }
}
