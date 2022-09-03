package com.sparta.miniproject2.service;

import com.sparta.miniproject2.domain.User;
import com.sparta.miniproject2.dto.UserRequestDto;
import com.sparta.miniproject2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userRequestDto.getUsername());
        if (optionalUser.isPresent()) {
            return "중복된 닉네임입니다.";
        }

        if (!userRequestDto.getPassword().equals(userRequestDto.getPasswordConfirm())) {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        } else {
            passwordEncoder.encode(userRequestDto.getPassword());
        }

        User user = new User(userRequestDto);
        userRepository.save(user);
        return "redirect:/user/login";
    }
}