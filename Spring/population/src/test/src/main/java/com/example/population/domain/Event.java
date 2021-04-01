package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "event")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "db", nullable = false)
    private String db;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "definer", nullable = false)
    private String definer;

    @Column(name = "execute_at")
    private Date executeAt;

    @Column(name = "interval_value")
    private Integer intervalValue;

    @Column(name = "interval_field")
    private String intervalField;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;

    @Column(name = "last_executed")
    private Date lastExecuted;

    @Column(name = "starts")
    private Date starts;

    @Column(name = "ends")
    private Date ends;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "on_completion", nullable = false)
    private String onCompletion;

    @Column(name = "sql_mode", nullable = false)
    private String sqlMode;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "originator", nullable = false)
    private Long originator;

    @Column(name = "time_zone", nullable = false)
    private String timeZone;

    @Column(name = "character_set_client")
    private String characterSetClient;

    @Column(name = "collation_connection")
    private String collationConnection;

    @Column(name = "db_collation")
    private String dbCollation;

    @Column(name = "body", nullable = false)
    private byte[] body;

    @Column(name = "body_utf8")
    private byte[] bodyUtf8;

}
