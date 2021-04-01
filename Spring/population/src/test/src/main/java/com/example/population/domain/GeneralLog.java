package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "general_log")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "general_log")
public class GeneralLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "event_time", nullable = false)
    private Date eventTime;

    @Column(name = "thread_id", nullable = false)
    private Long threadId;

    @Column(name = "server_id", nullable = false)
    private Long serverId;

    @Column(name = "command_type", nullable = false)
    private String commandType;

    @Column(name = "user_host", nullable = false)
    private String userHost;

    @Column(name = "argument", nullable = false)
    private byte[] argument;

}
