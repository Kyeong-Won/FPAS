package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "slave_relay_log_info")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "slave_relay_log_info")
public class SlaveRelayLogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The channel on which the slave is connected to a source. Used in Multisource Replication
     */
    @Id
    @Column(name = "Channel_name", nullable = false)
    private String channelName;

    /**
     * Number of lines in the file or rows in the table. Used to version table definitions.
     */
    @Column(name = "Number_of_lines", nullable = false)
    private Long numberOfLines;

    /**
     * The relay log position of the last executed event.
     */
    @Column(name = "Relay_log_pos", nullable = false)
    private Long relayLogPos;

    /**
     * The master log position of the last executed event.
     */
    @Column(name = "Master_log_pos", nullable = false)
    private Long masterLogPos;

    /**
     * The number of seconds that the slave must lag behind the master.
     */
    @Column(name = "Sql_delay", nullable = false)
    private Integer sqlDelay;

    @Column(name = "Number_of_workers", nullable = false)
    private Long numberOfWorkers;

    /**
     * Internal Id that uniquely identifies this record.
     */
    @Column(name = "Id", nullable = false)
    private Long id;

    /**
     * The name of the current relay log file.
     */
    @Column(name = "Relay_log_name", nullable = false)
    private String relayLogName;

    /**
     * The name of the master binary log file from which the events in the relay log file were read.
     */
    @Column(name = "Master_log_name", nullable = false)
    private String masterLogName;

}
