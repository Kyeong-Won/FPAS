package com.example.population.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DashBoard {
    @Id
    @GeneratedValue
    @Column(name = "dashboard_id")
    private Long id;

    private String link;

    @OneToOne(mappedBy = "dashBoard", fetch = FetchType.LAZY)
    private Camera camera;
}
