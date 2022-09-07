package com.sparta.miniproject2.service;

import com.sparta.miniproject2.crawling.YoutubeCrawling;
import com.sparta.miniproject2.domain.Comment;
import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.domain.Post;
import com.sparta.miniproject2.dto.request.PostRequestDto;
import com.sparta.miniproject2.dto.response.CommentResponseDto;
import com.sparta.miniproject2.dto.response.PostResponseDto;
import com.sparta.miniproject2.dto.response.ResponseDto;
import com.sparta.miniproject2.jwt.TokenProvider;
import com.sparta.miniproject2.repository.CommentRepository;
import com.sparta.miniproject2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TokenProvider tokenProvider;
    private final YoutubeCrawling youtubeCrawling;

    private final CommentRepository commentRepository;
    // 게시글 생성
    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND","로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND","로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN","Token이 유효하지 않습니다.");
        }

        boolean check = false;
        boolean urlCheck = youtubeCrawling.urlCheck(requestDto.getYoutubeUrl());
        if (check == urlCheck) {
            return ResponseDto.fail("NOT_YOUTUBE_LINK","유튜브 링크가 아닙니다.");
        }

        String thumbnailUrl = youtubeCrawling.crawling(requestDto.getYoutubeUrl());
        Post post = new Post(requestDto, thumbnailUrl, member);
        postRepository.save(post);
        return ResponseDto.success(
                PostResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .youtubeUrl(post.getYoutubeUrl())
                        .youtubeThumbnailUrl(post.getYoutubeThumbnailUrl())
                        .createdAt(post.getCreatedAt())
                        .modifiedAt(post.getModifiedAt())
                        .build()
        );
    }

    // 게시글 전체 조회
    @Transactional
    public ResponseDto<?> getAllPost() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();
        for (Post post : postList) {
            postResponseDto.add(
                    PostResponseDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .youtubeUrl(post.getYoutubeUrl())
                            .youtubeThumbnailUrl(post.getYoutubeThumbnailUrl())
                            .createdAt(post.getCreatedAt())
                            .modifiedAt(post.getModifiedAt())
                            .build()
            );
        }
        return ResponseDto.success(postResponseDto);
        //return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }

    // 게시글 개별 조회
    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long id) {
        Post post = isPresentPost(id);
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }
        List<Comment> commentList = commentRepository.findAllByPost(post);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment: commentList){
            commentResponseDtoList.add(
                    CommentResponseDto
                            .builder()
                            .id(comment.getId())
                            .username(comment.getMember().getUsername())
                            .nickname(comment.getMember().getNickname())
                            .content(comment.getContent())
                            .build()
            );
        }
        return ResponseDto.success(
                PostResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .youtubeUrl(post.getYoutubeUrl())
                        .youtubeThumbnailUrl(post.getYoutubeThumbnailUrl())
                        .commentList(commentResponseDtoList)
                        .createdAt(post.getCreatedAt())
                        .modifiedAt(post.getModifiedAt())
                        .build()
        );
    }

    //게시글 수정
    @Transactional
    public ResponseDto<?> updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }
        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Post post = isPresentPost(id);
        if (post == null) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }
        if (post.validateMember(member)) {
            return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
        }
        String thumbnailUrl = youtubeCrawling.crawling(requestDto.getYoutubeUrl());
        post.update(requestDto, thumbnailUrl);
        return ResponseDto.success(post);
    }

        //게시글 삭제
        @Transactional
        public ResponseDto<?> deletePost (Long id, HttpServletRequest request){
            if (null == request.getHeader("Refresh-Token")) {
                return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
            }

            if (null == request.getHeader("Authorization")) {
                return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
            }

            Member member = validateMember(request);
            if (null == member) {
                return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
            }

            Post post = isPresentPost(id);
            if (null == post) {
                return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
            }
            if (post.validateMember(member)) {
                return ResponseDto.fail("BAD_REQUEST", "작성자만 삭제할 수 있습니다.");
            }
            postRepository.delete(post);
            return ResponseDto.success("delete success");
        }

        @Transactional(readOnly = true)
        public Post isPresentPost(Long id){
            Optional<Post> optionalPost = postRepository.findById(id);
            return optionalPost.orElse(null);
        }

        @Transactional
        public Member validateMember(HttpServletRequest request){
            if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
                return null;
            }
            return tokenProvider.getMemberFromAuthentication();
        }

    }
