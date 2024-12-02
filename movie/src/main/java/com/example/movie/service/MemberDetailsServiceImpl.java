package com.example.movie.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordDto;
import com.example.movie.entity.Member;
import com.example.movie.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MemberDetailsServiceImpl implements UserDetailsService, MemberService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("service username : {}", username);

        // 로그인 요청
        Optional<Member> result = memberRepository.findByEmail(username);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("이메일 확인");
        }

        // 이메일이 존재한다면 entity => dto
        Member member = result.get();

        MemberDto memberDto = MemberDto.builder()
                .mid(member.getMid())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .role(member.getRole()).build();

        return new AuthMemberDto(memberDto);
    }

    @Transactional
    @Override
    public void nickNameUpdate(MemberDto memberDto) {

        memberRepository.updateNickName(memberDto.getNickname(), memberDto.getEmail());
    }

    @Override
    public void passwordUpdate(PasswordDto passwordDto) throws Exception {
        // email 을 이용해 사용자 찾기
        // Optional<Member> result =
        // memberRepository.findByEmail(passwordDto.getEmail());
        // if (!result.isPresent()) throw new UsernameNotFoundException("이메일 확인");
        Member member = memberRepository.findByEmail(passwordDto.getEmail()).get();

        // 현재 비밀번호가 입력한 비밀번호와 일치 하는지 검증
        if (!passwordEncoder.matches(member.getPassword(), passwordDto.getCurrentPassword())) {
            // false : 되돌려 보내기
            throw new Exception("현재 비밀번호를 확인");
        } else {
            // true : 새비밀번호를 수정
            member.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
            memberRepository.save(member);
        }

    }

}
