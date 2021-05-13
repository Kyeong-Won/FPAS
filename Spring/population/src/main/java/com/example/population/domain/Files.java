package com.example.population.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Files {
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    String filename;
    String fileoriName;
    String fileurl;
}
