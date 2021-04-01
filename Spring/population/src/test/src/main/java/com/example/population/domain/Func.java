package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "func")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "func")
public class Func implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ret", nullable = false)
    private Boolean ret;

    @Column(name = "dl", nullable = false)
    private String dl;

    @Column(name = "type", nullable = false)
    private String type;

}
