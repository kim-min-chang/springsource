package com.example.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(exclude = "board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reply extends BaseEntity {

    @SequenceGenerator(name = "board_reply_gen", sequenceName = "board_reply_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_reply_seq_gen")
    @Id
    private Long rno;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String replyer;

    // fetch=>EAGER
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
