package com.sparta.miniproject2.repository;

import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByMember(Member member);
}