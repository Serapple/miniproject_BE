package com.sparta.miniproject2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    @Pattern(regexp = "[a-zA-Z\\d]*${3,12}")
    @Size(min = 4, max = 12)
    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @Pattern(regexp = "[a-zA-Z\\d]*${3,32}")
    @Size(min = 4, max = 32)
    @NotBlank
    private String password;

    @NotBlank
    private String passwordConfirm;
}