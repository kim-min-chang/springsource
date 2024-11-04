package com.example.mart.entity.sports;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "sports_locker")
@SequenceGenerator(name = "sports_locker_seq_gen", sequenceName = "locker_seq", allocationSize = 1)
public class Locker {
    @Column(name = "locker_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sports_locker_seq_gen")
    private Long id;

    private String name;

}
