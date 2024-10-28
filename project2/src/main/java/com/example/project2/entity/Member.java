package com.example.project2.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.project2.entity.constant.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 회원테이블
// id, name, age
// 회원가입일, 수정일이 필요
// 회원 - 관리자,회원로 구분됨

@Entity(name = "membertbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "member_seq_gen", sequenceName = "member_seq", allocationSize = 1)
public class Member {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_gen")
    @Id
    private String id;

    @Column(name = "name")
    private String username;

    private int age;

    private RoleType roleType;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
