package com.sparta.miniproject2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String youtubeUrl;

}