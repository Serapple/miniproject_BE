package com.sparta.miniproject2.repository;

import com.sparta.miniproject2.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAt();

   List<Post> findAllByOrderByModifiedAt();

    List<Post> findAllByOrderByModifiedAtDesc();
}