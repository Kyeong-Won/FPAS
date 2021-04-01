package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "tables_priv")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "tables_priv")
public class TablesPriv implements Serializable {

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
    @Column(name = "Table_name", nullable = false)
    private String tableName;

    @Column(name = "Grantor", nullable = false)
    private String grantor;

    @Column(name = "Timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "Table_priv", nullable = false)
    private String tablePriv;

    @Column(name = "Column_priv", nullable = false)
    private String columnPriv;

}
