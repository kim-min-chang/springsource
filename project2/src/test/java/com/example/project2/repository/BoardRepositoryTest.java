package com.example.project2.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 20)
                .forEach(i -> {
                    Board board = Board.builder()
                            .title("제목" + i)
                            .content("내용" + i)
                            .wirter("김")
                            .build();
                    boardRepository.save(board);
                });
    }

    @Test
    public void selectOneTest() {
        Board board = boardRepository.findById(15L).get();
        System.out.println(board);
    }

    @Test
    public void selectAllTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void updateTest() {
        Board board = boardRepository.findById(5L).get();
        board.setTitle("제목변경");
        board.setWirter("작가변경");

        boardRepository.save(board);
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(20L);
    }
}
