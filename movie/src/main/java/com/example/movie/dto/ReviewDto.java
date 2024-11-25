package com.example.movie.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewNo;

    private int grade;

    private String text;

    // movie 의 mno 담기
    private Long mno;

    // member mid, nickname,email
    private Long mid;
    private String nickname;
    private String email;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
