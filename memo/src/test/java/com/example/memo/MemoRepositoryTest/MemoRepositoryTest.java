package com.example.memo.MemoRepositoryTest;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testMemoInsert() {
        LongStream.rangeClosed(11, 90).forEach(i -> {
            Memo memo = Memo.builder().memoText("memo" + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testMemoRead() {
        // 특정 메모6 가져오기
        System.out.println(memoRepository.findById(6L));
        // 전체 메모 가져오기
        memoRepository.findAll().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void testMemoUpdate() {
        // 7번 메모 내용 수정
        Memo memo = memoRepository.findById(7L).get();
        memo.setMemoText("내용 수정");
        memoRepository.save(memo);
    }

    @Test
    public void testMemoDelete() {
        // 마지막 메모 삭제
        memoRepository.deleteById(10L);
    }

    @Test
    public void testMnoList() {
        memoRepository.findByMnoLessThan(5L).forEach(m -> System.out.println(m));
        memoRepository.findByMnoLessThanOrderByMnoDesc(10L).forEach(m -> System.out.println(m));
        memoRepository.findByMnoBetween(50L, 100L).forEach(m -> System.out.println(m));

    }
}
