package com.sparta.miniproject2.service;

import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.dto.LoginRequestDto;
import com.sparta.miniproject2.dto.MemberRequestDto;
import com.sparta.miniproject2.dto.TokenDto;
import com.sparta.miniproject2.jwt.TokenProvider;
import com.sparta.miniproject2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    @Transactional
    public String createMember(MemberRequestDto memberRequestDto) {
        Optional<Member> optionalMember = memberRepository.findByUsername(memberRequestDto.getUsername());
        if (optionalMember.isPresent()) {
            return "중복된 아이디입니다.";
        }
        Optional<Member> optionalNickname = memberRepository.findByNickname(memberRequestDto.getNickname());
        if(optionalNickname.isPresent()){
            return "중복된 닉네임입니다";
        }
        if (!memberRequestDto.getPassword().equals(memberRequestDto.getPasswordConfirm())) {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }

        String pw = passwordEncoder.encode(memberRequestDto.getPassword());

        Member member = new Member(memberRequestDto, pw);
        memberRepository.save(member);
        return "redirect:/api/member/login";
    }

    @Transactional
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Member member = isPresentMember(loginRequestDto.getUsername());
        if (null == member) {
            return "사용자를 찾을 수 없습니다.";
        }

        if (!member.validatePassword(passwordEncoder, loginRequestDto.getPassword())) {
            return "사용자를 찾을 수 없습니다.";
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        tokenToHeaders(tokenDto, response);

        return "redirect:/api/post";
    }

    @Transactional
    public String logout(HttpServletRequest request) {
        if(!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return "Token이 유효하지 않습니다.";
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if (null == member) {
            return "사용자를 찾을 수 없습니다.";
        }
        return tokenProvider.deleteRefreshToken(member);
    }

    @Transactional
    public Object login(LoginRequestDto requestDto, HttpServletResponse response){
        Member member = isPresentMember(requestDto.getUsername());
        if(member == null){
            return "존재하지 않는 아이디입니다.";
        }
        if(!member.validatePassword(passwordEncoder, requestDto.getPassword())){
            return "비밀번호를 확인해주세요";
        }
        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        tokenToHeaders(tokenDto, response);
        return "redirect:/api/post";
    }


    @Transactional(readOnly = true)
    public Member isPresentMember(String username) {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        return optionalMember.orElse(null);
    }

    public void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
    }


}