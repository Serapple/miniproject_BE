package com.sparta.miniproject2.controller;

import com.sparta.miniproject2.dto.UserRequestDto;
import com.sparta.miniproject2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user/signup")
    public String signup(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

}