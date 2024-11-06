package com.example.mart.entity.sports;

import com.example.mart.entity.item.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
@ToString(exclude = "locker")
@Setter
@Getter
@Entity
@Table(name = "sports_member")
@SequenceGenerator(name = "sports_member_seq_gen", sequenceName = "sports_member_seq", allocationSize = 1)
public class SportsMember extends BaseEntity {

    @Column(name = "member_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sports_member_seq_gen")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Locker locker;
}
