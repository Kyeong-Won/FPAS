package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "server_cost")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "server_cost")
public class ServerCost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cost_name", nullable = false)
    private String costName;

    @Column(name = "cost_value")
    private Float costValue;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @Column(name = "comment")
    private String comment;

}
