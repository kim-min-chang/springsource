package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto {

    private Long rno;

    private String text;

    private String replyer;

    // private Board bno
    private Long bno; // 게시판 번호 (부모)

    private LocalDateTime regDate;
    private LocalDateTime updateTime;

}
