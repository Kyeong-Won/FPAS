package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "ndb_binlog_index")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "ndb_binlog_index")
public class NdbBinlogIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "epoch", nullable = false)
    private Long epoch;

    @Id
    @Column(name = "orig_server_id", nullable = false)
    private Long origServerId;

    @Id
    @Column(name = "orig_epoch", nullable = false)
    private Long origEpoch;

    @Column(name = "Position", nullable = false)
    private Long position;

    @Column(name = "File", nullable = false)
    private String file;

    @Column(name = "inserts", nullable = false)
    private Long inserts;

    @Column(name = "updates", nullable = false)
    private Long updates;

    @Column(name = "deletes", nullable = false)
    private Long deletes;

    @Column(name = "schemaops", nullable = false)
    private Long schemaops;

    @Column(name = "gci", nullable = false)
    private Long gci;

    @Column(name = "next_position", nullable = false)
    private Long nextPosition;

    @Column(name = "next_file", nullable = false)
    private String nextFile;

}
