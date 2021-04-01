package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "time_zone_name")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "time_zone_name")
public class TimeZoneName implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Time_zone_id", nullable = false)
    private Long timeZoneId;

}
