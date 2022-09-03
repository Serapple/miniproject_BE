package com.sparta.miniproject2.service;

import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.dto.MemberRequestDto;
import com.sparta.miniproject2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String createUser(MemberRequestDto userRequestDto) {
        Optional<Member> optionalUser = memberRepository.findByUsername(userRequestDto.getUsername());
        if (optionalUser.isPresent()) {
            return "중복된 닉네임입니다.";
        }

        if (!userRequestDto.getPassword().equals(userRequestDto.getPasswordConfirm())) {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        } else {
            passwordEncoder.encode(userRequestDto.getPassword());
        }

        Member user = new Member(userRequestDto);
        memberRepository.save(user);
        return "redirect:/api/member/login";
    }
}