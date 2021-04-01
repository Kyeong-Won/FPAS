package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "procs_priv")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "procs_priv")
public class ProcsPriv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Host", nullable = false)
    private String host;

    @Id
    @Column(name = "Db", nullable = false)
    private String db;

    @Id
    @Column(name = "User", nullable = false)
    private String user;

    @Id
    @Column(name = "Routine_name", nullable = false)
    private String routineName;

    @Id
    @Column(name = "Routine_type", nullable = false)
    private String routineType;

    @Column(name = "Grantor", nullable = false)
    private String grantor;

    @Column(name = "Proc_priv", nullable = false)
    private String procPriv;

    @Column(name = "Timestamp", nullable = false)
    private Date timestamp;

}
