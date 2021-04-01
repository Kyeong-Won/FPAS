package com.example.population.domain;

import lombok.Data;

import java.io.Serializable;

@javax.persistence.Table(name = "time_zone")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "time_zone")
public class TimeZone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Time_zone_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeZoneId;

    @Column(name = "Use_leap_seconds", nullable = false)
    private String useLeapSeconds;

}
