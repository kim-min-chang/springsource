package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.entity.Board;
import com.example.board.entity.QBoard;

public interface BoardRepository extends JpaRepository<Board, Long> {

    QBoard qBoard = QBoard.board;
}
