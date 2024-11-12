package com.example.project2.entity.jpql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@SequenceGenerator(name = "jpql_seq_gen", sequenceName = "jpql_seq", allocationSize = 1)
@Table(name = "jpql_team")
public class Team {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpql_seq_gen")
    @Id
    private Long id;

    private String name;
}
