package com.example.mart.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.sports.Locker;
import com.example.mart.entity.sports.SportsMember;
import com.example.mart.repository.sports.LockerRepository;
import com.example.mart.repository.sports.SportsMemberRepository;

@SpringBootTest
public class LockerRepositoryTest {

    @Autowired
    private SportsMemberRepository sportsMemberRepository;

    @Autowired
    private LockerRepository lockerRepository;

    @Test
    public void testLockerInsert() {
        // locker4
        LongStream.rangeClosed(1, 4).forEach(i -> {
            Locker locker = Locker.builder()
                    .name("Locker" + i)
                    .build();
            lockerRepository.save(locker);
        });
        // member4
        LongStream.rangeClosed(1, 4).forEach(i -> {
            Locker locker = Locker.builder().id(i).build();
            SportsMember member = SportsMember.builder()
                    .name("user" + i)
                    .locker(locker)
                    .build();
            sportsMemberRepository.save(member);
        });
    }
}
