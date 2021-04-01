package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "slave_worker_info")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "slave_worker_info")
public class SlaveWorkerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The channel on which the slave is connected to a source. Used in Multisource Replication
     */
    @Id
    @Column(name = "Channel_name", nullable = false)
    private String channelName;

    @Id
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Relay_log_pos", nullable = false)
    private Long relayLogPos;

    @Column(name = "Master_log_pos", nullable = false)
    private Long masterLogPos;

    @Column(name = "Checkpoint_relay_log_pos", nullable = false)
    private Long checkpointRelayLogPos;

    @Column(name = "Checkpoint_master_log_pos", nullable = false)
    private Long checkpointMasterLogPos;

    @Column(name = "Checkpoint_seqno", nullable = false)
    private Long checkpointSeqno;

    @Column(name = "Checkpoint_group_size", nullable = false)
    private Long checkpointGroupSize;

    @Column(name = "Relay_log_name", nullable = false)
    private String relayLogName;

    @Column(name = "Master_log_name", nullable = false)
    private String masterLogName;

    @Column(name = "Checkpoint_relay_log_name", nullable = false)
    private String checkpointRelayLogName;

    @Column(name = "Checkpoint_master_log_name", nullable = false)
    private String checkpointMasterLogName;

    @Column(name = "Checkpoint_group_bitmap", nullable = false)
    private byte[] checkpointGroupBitmap;

}
