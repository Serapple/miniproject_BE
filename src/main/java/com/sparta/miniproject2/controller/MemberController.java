package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.dto.LoginRequestDto;
import com.sparta.miniproject2.dto.MemberRequestDto;
import com.sparta.miniproject2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/api/member/signup")
    public String signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.createMember(memberRequestDto);
<<<<<<< Updated upstream
=======
    }

    @PostMapping(value = "/api/member/login")
    public String login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.login(loginRequestDto, response);
>>>>>>> Stashed changes
    }

    @PostMapping(value = "/api/auth/member/logout")
    public String logout(HttpServletRequest request) {
        return memberService.logout(request);
    }

    @PostMapping(value = "/api/member/login")
    public Object login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse response){
        return memberService.login(requestDto, response);
    }
}