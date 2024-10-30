package com.example.project2.repository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Item;
import com.example.project2.entity.constant.ItemStatus;

import jakarta.persistence.Column;

@SpringBootTest
public class ItemREpositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    Item item = Item.builder()
                            .itemNm("운동화" + i)
                            .price(95000)
                            .stockNumber(15)
                            .itemSellStatus(ItemStatus.SELL)
                            .regTime(LocalDateTime.now())
                            .build();
                    itemRepository.save(item);
                });
    }

    @Test
    public void selectOneTest() {
        // id가 5인 운동화 조회
        Item item = itemRepository.findById(5L).get();
        System.out.println(item);
    }

    @Test
    public void selectAllTest() {
        itemRepository.findAll().forEach(member -> System.out.println(member));
    }

    @Test
    public void updateTest() {
        // id가 6 인 운동화 업데이트
        // 가격 90000, updateTime
        Item item = itemRepository.findById(6L).get();
        item.setUpdateTime(LocalDateTime.now());
        item.setPrice(90000);

        itemRepository.save(item);
    }

    @Test
    public void deleteTest() {
        // id가 9인 운동화 삭제
        itemRepository.deleteById(9L);
    }
}
