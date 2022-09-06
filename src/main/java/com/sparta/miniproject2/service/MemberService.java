package com.sparta.miniproject2.service;

import com.sparta.miniproject2.domain.Member;
import com.sparta.miniproject2.domain.RefreshToken;
import com.sparta.miniproject2.dto.request.LoginRequestDto;
import com.sparta.miniproject2.dto.request.MemberRequestDto;
import com.sparta.miniproject2.dto.response.MemberResponseDto;
import com.sparta.miniproject2.dto.response.ResponseDto;
import com.sparta.miniproject2.dto.TokenDto;
import com.sparta.miniproject2.jwt.TokenProvider;
import com.sparta.miniproject2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    @Transactional
    public ResponseDto<?> createMember(MemberRequestDto requestDto) {
        if (isPresentMember(requestDto.getUsername()) != null) {
            return ResponseDto.fail("OVERLAP_USERNAME", "중복된 아이디입니다.");
        }
        if (isPresentNickname(requestDto.getNickname()) != null) {
            return ResponseDto.fail("OVERLAP_NICKNAME", "중복된 닉네임입니다.");
        }
        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            return ResponseDto.fail("MISMATCH_PASSWORD","비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        String pw = passwordEncoder.encode(requestDto.getPassword());
        Member member = new Member(requestDto, pw);
        memberRepository.save(member);

        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .username(requestDto.getUsername())
                        .nickname(requestDto.getNickname())
                        .build()
        );
    }


    @Transactional
    public ResponseDto<?> logout(HttpServletRequest request) {
        if(!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return ResponseDto.fail("INVALID_TOKEN","Token이 유효하지 않습니다.");
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if (null == member) {
            return ResponseDto.fail("NOT_FOUND_USER","사용자를 찾을 수 없습니다.");
        }

        return ResponseDto.success(
                tokenProvider.deleteRefreshToken(member)
        );
    }

    @Transactional
    public ResponseDto<?> login(LoginRequestDto requestDto, HttpServletResponse response){
        Member member = isPresentMember(requestDto.getUsername());
        if(member == null){
            return ResponseDto.fail("NOT_FOUND_USERNAME", "존재하지 않는 아이디입니다.");
        }
        if(!member.validatePassword(passwordEncoder, requestDto.getPassword())){
            return ResponseDto.fail("MISMATCH_PASSWORD", "비밀번호를 확인해주세요");
        }
        TokenDto tokenDto = tokenProvider.generateTokenDto(member);
        tokenToHeaders(tokenDto, response);
        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .username(member.getUsername())
                        .nickname(member.getNickname())
                        .accessToken(tokenDto.getAccessToken()) //프론트와 협의해보기
                        .refreshToken(tokenDto.getRefreshToken()) //프론트와 협의해보기
                        .build()
        );
    }


    @Transactional(readOnly = true)
    public Member isPresentMember(String username) {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        return optionalMember.orElse(null);
    }
    
    @Transactional(readOnly = true)
    public Member isPresentNickname(String nickname){
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        return optionalMember.orElse(null);
    }

    public void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());
        //response.addHeader("Access-Control-Allow-Origin", "https://miniproject-nine.vercel.app");
    }


}