package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "time_zone_transition")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "time_zone_transition")
public class TimeZoneTransition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Time_zone_id", nullable = false)
    private Long timeZoneId;

    @Id
    @Column(name = "Transition_time", nullable = false)
    private Long transitionTime;

    @Column(name = "Transition_type_id", nullable = false)
    private Long transitionTypeId;

}
