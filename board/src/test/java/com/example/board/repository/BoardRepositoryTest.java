package com.example.board.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

import jakarta.transaction.Transactional;

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
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Long bno = (long) (Math.random() * 100) + 1;
            Board board = boardRepository.findById(bno).get();
            Reply reply = Reply.builder()
                    .text("reply" + i)
                    .replyer("guest" + i)
                    .board(board)
                    .build();
            replyRepository.save(reply);
        });

    }

    @Transactional
    @Test
    public void testReadBorad() {
        // 100번
        Board board = boardRepository.findById(100L).get();
        System.out.println(board);

        // 객체 그래프 탐색 : Board, Member 관계
        System.out.println(board.getWriter());

    }

    @Transactional
    @Test
    public void testReadReply() {
        // 100번
        Reply reply = replyRepository.findById(100L).get();
        System.out.println(reply);

        System.out.println(reply.getBoard());
        System.out.println(reply.getBoard().getWriter());
    }

    @Transactional
    @Test
    public void testReadBoardReply() {
        Board board = boardRepository.findById(100L).get();
        System.out.println(board);

        System.out.println(board.getReplys());
    }

    @Test
    public void testJoin() {
        List<Object[]> result = boardRepository.list();

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
            // Board board = (Board) objects[0];
            // Member member = (Member) objects[1];
            // Long replyCnt = (Long) objects[2];

        }
    }

    @Test
    public void testJoinList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.list("tc", "content", pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));

        }
    }

    @Test
    public void testJoinRow() {
        Object[] object = boardRepository.getBoardByBno(100L);
        System.out.println(Arrays.toString(object));

    }

    @Commit
    @Transactional
    @Test
    public void testReplyRemove() {
        replyRepository.deleteByBno(34L);
        boardRepository.deleteById(34L);
    }

    @Test
    public void testReplyRemove2() {
        boardRepository.deleteById(15L);
    }

    @Test
    public void testReplyList() {
        Board board = Board.builder().bno(10L).build();
        List<Reply> list = replyRepository.findByBoardOrderByRno(board);

        list.forEach(b -> System.out.println(b));
    }

    @Test
    public void testReplyUpdate() {
        // 댓글 수정
        Reply reply = replyRepository.findById(954L).get();
        System.out.println("reply " + reply);
        // 내용 수정
        reply.setText("내용수정");
        replyRepository.save(reply);
        System.out.println(replyRepository.save(reply));

    }
}
