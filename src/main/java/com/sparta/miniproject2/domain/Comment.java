package com.sparta.miniproject2.domain;

import com.sparta.miniproject2.dto.request.CommentRequestDto;
import com.sparta.miniproject2.dto.request.PostRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(nullable = false)
    private String content;

    public Comment(CommentRequestDto requestDto, Post post, Member member) {
        this.content = requestDto.getContent();
        this.post = post;
        this.member = member;
    }
    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }
}