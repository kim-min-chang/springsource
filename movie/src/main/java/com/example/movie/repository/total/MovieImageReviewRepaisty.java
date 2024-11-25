package com.example.movie.repository.total;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieImageReviewRepaisty {
    // 페이지 나누기+검색
    Page<Object[]> getTotalList(String type, String keyword, Pageable pageable);

    // 특정 영화 접오 조회
    List<Object[]> getMovieRow(Long mno);

}
