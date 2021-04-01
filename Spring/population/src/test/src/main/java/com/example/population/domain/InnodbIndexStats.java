package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "innodb_index_stats")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "innodb_index_stats")
public class InnodbIndexStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "database_name", nullable = false)
    private String databaseName;

    @Id
    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Id
    @Column(name = "index_name", nullable = false)
    private String indexName;

    @Id
    @Column(name = "stat_name", nullable = false)
    private String statName;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @Column(name = "stat_value", nullable = false)
    private Long statValue;

    @Column(name = "sample_size")
    private Long sampleSize;

    @Column(name = "stat_description", nullable = false)
    private String statDescription;

}
