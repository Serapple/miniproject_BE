package com.sparta.miniproject2.repository;

import com.sparta.miniproject2.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}