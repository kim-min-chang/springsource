package com.example.board.repository;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertMember() {
        // 30개
        IntStream.rangeClosed(1, 30).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@google.com")
                    .name("user" + i)
                    .password("1111")
                    .build();
            memberRepository.save(member);
        });

    }

    @Test
    public void testInsertBoard() {
        // 100개

        int num = (int) (Math.random() * 30) + 1;
        Member member = memberRepository.findById("user" + num + "@google.com").get();
        IntStream.rangeClosed(21, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });

    }

    @Test
    public void testInsertReply() {
        // 100개
        Long bno = (long) (Math.random() * 100) + 1;
        Board board = boardRepository.findById(bno).get();
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Reply reply = Reply.builder()
                    .text("reply" + i)
                    .replyer("guest" + i)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });

    }
}
