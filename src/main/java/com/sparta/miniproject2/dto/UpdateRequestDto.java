package com.sparta.miniproject2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequestDto {
    @NotBlank
    private String content;
}