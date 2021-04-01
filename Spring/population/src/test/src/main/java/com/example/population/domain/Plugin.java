package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "plugin")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "plugin")
public class Plugin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dl", nullable = false)
    private String dl;

}
