package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@javax.persistence.Table(name = "slow_log")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "slow_log")
public class SlowLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "query_time", nullable = false)
    private Time queryTime;

    @Column(name = "lock_time", nullable = false)
    private Time lockTime;

    @Column(name = "rows_sent", nullable = false)
    private Integer rowsSent;

    @Column(name = "rows_examined", nullable = false)
    private Integer rowsExamined;

    @Column(name = "db", nullable = false)
    private String db;

    @Column(name = "last_insert_id", nullable = false)
    private Integer lastInsertId;

    @Column(name = "insert_id", nullable = false)
    private Integer insertId;

    @Column(name = "server_id", nullable = false)
    private Long serverId;

    @Column(name = "thread_id", nullable = false)
    private Long threadId;

    @Column(name = "user_host", nullable = false)
    private String userHost;

    @Column(name = "sql_text", nullable = false)
    private byte[] sqlText;

}
