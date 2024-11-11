package com.example.memo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.memo.dto.MemoDto;
import com.example.memo.entity.Memo;

@Service
public interface MemoService {
    // crud 메소드

    Long create(MemoDto dto);

    MemoDto read(Long id);

    List<MemoDto> list();

    Long update(MemoDto dto);

    void delete(Long id);

    // dto ==> entity변환
    public default Memo dtoToEntity(MemoDto dto) {
        return Memo.builder()
                .mno(dto.getId())
                .memoText(dto.getMemoText())
                .build();
    }

    // entity ==> dto 변환
    public default MemoDto entityToDto(Memo memo) {
        return MemoDto.builder()
                .id(memo.getMno())
                .memoText(memo.getMemoText())
                .createdDateTime(memo.getCreatedDateTime())
                .lastModifiedDateTime(memo.getLastModifideDateTime())
                .build();
    }
}
