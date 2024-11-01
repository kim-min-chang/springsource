package com.example.project3.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "team")
@Table(name = "team_member")
@Entity // Entity 를 @Query 사용할때 여기서 지정한 이름으로 접근해야함
public class Member {

    @Id
    private String id;

    private String username;

    // 관계
    // 주인이 누구 - @ManyToOne를 설정한 entity
    @ManyToOne(cascade = CascadeType.ALL)
    private Team team; // SQL 의 외래키
}
