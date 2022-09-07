package com.sparta.miniproject2.repository;

import com.sparta.miniproject2.domain.Comment;
import com.sparta.miniproject2.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
}
