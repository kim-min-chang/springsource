package com.example.movie.entity;

import com.example.movie.entity.constant.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "M_Member")
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @SequenceGenerator(name = "movie_member_seq_gen", sequenceName = "movie_member_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_member_seq_gen")
    private Long mid;

    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberRole Role;
}
