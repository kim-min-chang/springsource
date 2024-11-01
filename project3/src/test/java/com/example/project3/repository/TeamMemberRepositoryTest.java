package com.example.project3.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project3.entity.Member;
import com.example.project3.entity.Team;

import jakarta.transaction.Transactional;
import oracle.net.aso.m;

@SpringBootTest
public class TeamMemberRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        Team team = Team.builder()
                .id("team1")
                .name("team1")
                .build();
        teamRepository.save(team);

        team = Team.builder()
                .id("team2")
                .name("team2")
                .build();
        teamRepository.save(team);
    }

    @Test
    public void createMemberTest() {

        Team team1 = teamRepository.findById("team1").get();
        Team team2 = Team.builder().id("team2").build();

        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .username("성춘향" + i)
                    .team(team2)
                    .build();

            memberRepository.save(member);
        });
        IntStream.rangeClosed(6, 10).forEach(i -> {
            Member member = Member.builder()
                    .id("user" + i)
                    .username("성춘향" + i)
                    .team(team1)
                    .build();

            memberRepository.save(member);
        });

    }

    @Test
    public void selectTest() {
        // 회원 찾기
        Member member = memberRepository.findById("user1").get();
        System.out.println(member);

        // 팀 정보
        System.out.println(member.getTeam());
        // 팀명 찾기
        System.out.println(member.getTeam().getName());

    }

    @Test
    public void memberEqualTeamTest() {

        memberRepository.findbyMemberEqualTeam("team1").forEach(m -> System.out.println(m));
    }

    @Test
    public void updateTest() {

        // user6의 팀 변경하기 team2 로
        Member member = memberRepository.findById("user6").get();

        Team team2 = teamRepository.findById("team2").get();

        member.setTeam(team2);
        memberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        // team1 제거
        // teamRepository.deleteById("team1");

        // 외래키 제약조건에서는 자식부터 삭제
        // 자식의 팀 변경하던지 삭제하던지

        Team team = Team.builder().id("team1").build();
        List<Member> members = memberRepository.findByTeam(team);
        // members.forEach(member -> System.out.println(member));

        Team team2 = teamRepository.findById("team2").get();

        members.forEach(member -> {
            member.setTeam(team2);
            memberRepository.save(member);
        });
        teamRepository.deleteById("team1");
    }

    // member 삽입하면서 team 삽입이 가능한가?
    // sql 1) 부모삽입 2) 자식삽입
    // jpa 에서는 객체 형태로 다루니까
    @Test
    public void memeberAndTeamInsertTest() {
        Team team = Team.builder().id("team3").name("team3").build();
        Member member = Member.builder().id("user11").username("홍길동").team(team).build();
        memberRepository.save(member);
    }

    @Test
    public void memeberAndTeamUpdateTest() {
        Team team = teamRepository.findById("team3").get();
        team.setName("victory");
        // teamRepository.save(team);

        Member member = Member.builder().id("user11").username("홍길동").team(team).build();
        memberRepository.save(member);
    }

    // 양방향
    @Transactional
    @Test
    public void selectMemeberTest() {
        // 팀 찾기
        Team team2 = teamRepository.findById("team2").get();

        // left join 을 하지 않음 => member 정보 없음
        team2.getMembers().forEach(member -> {
            // 팀 정보 출력
            System.out.println(member);

            // 팀에 속한 member
            System.out.println(member.getUsername());
        });
    }

    @Test
    public void teamAndMemberInsertTest() {
        Team team = Team.builder().id("team3").name("team3").build();
        team.setName("victory");
        // teamRepository.save(team);

        Member member = Member.builder().id("user12").username("수선화").team(team).build();
        team.getMembers().add(member);
        teamRepository.save(team);

        // 원래 방식대로라면
        // Team team4 = Team.builder().id("team4").name("team4").build();
        // teamRepository.save(team4);
        // Member member2 =
        // Member.builder().id("user12").username("수선화").team(team).build();
        // memberRepository.save(member2);
    }
}
