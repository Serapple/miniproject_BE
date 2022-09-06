package com.sparta.miniproject2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String youtubeUrl;
    private String youtubeThumbnailUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}