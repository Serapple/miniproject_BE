package com.sparta.miniproject2.domain;

import com.sparta.miniproject2.dto.PostRequestDto;
import com.sparta.miniproject2.dto.UpdateRequestDto;
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
    private String thumbnail;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

<<<<<<< Updated upstream
    public Post(PostRequestDto requestDto, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.youtubeUrl = requestDto.getYoutubeUrl();
        this.member = member;
=======
    public Post(PostRequestDto requestDto, String thumbnail) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.youtubeUrl = requestDto.getYoutubeUrl();
        this.thumbnail = thumbnail;
>>>>>>> Stashed changes
    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }
    public void update(UpdateRequestDto requestDto){
        this.content = requestDto.getContent();
    }
}
