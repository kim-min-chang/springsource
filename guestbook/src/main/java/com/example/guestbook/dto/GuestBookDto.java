package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
@Getter
public class GuestBookDto {
    private Long gno;

    @NotEmpty(message = "title 은 필수 입력 요소입니다.")
    private String title;
    @NotEmpty(message = "content 는 필수 입력 요소입니다.")
    private String content;
    @NotEmpty(message = "wirter 는 필수 입력 요소입니다.")
    private String writer;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
