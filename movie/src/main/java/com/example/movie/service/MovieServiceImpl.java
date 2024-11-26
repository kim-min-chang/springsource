package com.example.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.repository.MovieImageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieImageRepository movieImageRepository;

    @Override
    public PageResultDto<MovieDto, Object[]> getList(PageRequestDto pageRequestDto) {

        Pageable pageable = pageRequestDto.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieImageRepository.getTotalList(null, null, pageable);

        Function<Object[], MovieDto> function = (en -> entityToDto((Movie) en[0],
                (List<MovieImage>) Arrays.asList((MovieImage) en[1]),
                (Long) en[2], (Double) en[3]));

        return new PageResultDto<>(result, function);
    }

    @Override
    public Long register(MovieDto movieDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public Long modify(MovieDto movieDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Override
    public void delete(Long mno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public MovieDto get(Long mno) {
        List<Object[]> result = movieImageRepository.getMovieRow(mno);
        Movie movie = (Movie) result.get(0)[0];
        Long rCnt = (Long) result.get(0)[2];
        Double avg = (Double) result.get(0)[3];

        // 1 : 영화 이미지
        List<MovieImage> movieImages = new ArrayList<>();
        result.forEach(row -> {
            MovieImage movieImage = (MovieImage) row[1];
            movieImages.add(movieImage);
        });

        return entityToDto(movie, movieImages, rCnt, avg);

    }

}
