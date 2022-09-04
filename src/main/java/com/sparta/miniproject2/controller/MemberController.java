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
        return memberService.createUser(memberRequestDto);
    }

    @PostMapping(value = "/api/auth/member/logout")
    public String logout(HttpServletRequest request) {
        return memberService.logout(request);
    }
}