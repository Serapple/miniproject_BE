package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.dto.MemberRequestDto;
import com.sparta.miniproject2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberServicee;

    @PostMapping(value = "/api/member/signup")
    public String signup(@RequestBody @Valid MemberRequestDto userRequestDto) {
        return memberServicee.createUser(userRequestDto);
    }

}