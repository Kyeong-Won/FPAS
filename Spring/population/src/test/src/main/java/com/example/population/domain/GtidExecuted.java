package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "gtid_executed")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "gtid_executed")
public class GtidExecuted implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid of the source where the transaction was originally executed.
     */
    @Id
    @Column(name = "source_uuid", nullable = false)
    private String sourceUuid;

    /**
     * First number of interval.
     */
    @Id
    @Column(name = "interval_start", nullable = false)
    private Long intervalStart;

    /**
     * Last number of interval.
     */
    @Column(name = "interval_end", nullable = false)
    private Long intervalEnd;

}
