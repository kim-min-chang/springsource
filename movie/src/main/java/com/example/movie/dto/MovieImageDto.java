package com.example.movie.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageDto {
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
