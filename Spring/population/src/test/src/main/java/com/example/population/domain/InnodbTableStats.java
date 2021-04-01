package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "innodb_table_stats")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "innodb_table_stats")
public class InnodbTableStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "database_name", nullable = false)
    private String databaseName;

    @Id
    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @Column(name = "n_rows", nullable = false)
    private Long nRows;

    @Column(name = "clustered_index_size", nullable = false)
    private Long clusteredIndexSize;

    @Column(name = "sum_of_other_index_sizes", nullable = false)
    private Long sumOfOtherIndexSizes;

}
