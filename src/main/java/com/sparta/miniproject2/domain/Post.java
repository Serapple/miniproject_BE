package com.sparta.miniproject2.domain;

import com.sparta.miniproject2.dto.request.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String youtubeUrl;

    @Column(nullable = false)
    private String youtubeThumbnailUrl;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Post(PostRequestDto requestDto, String thumbnail, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.youtubeUrl = requestDto.getYoutubeUrl();
        this.youtubeThumbnailUrl = thumbnail;
        this.member = member;

    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }
    public void update(PostRequestDto requestDto, String thumbnail){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.youtubeUrl = requestDto.getYoutubeUrl();
        this.youtubeThumbnailUrl = thumbnail;
    }
}
