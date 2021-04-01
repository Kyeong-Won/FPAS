package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "proc")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "proc")
public class Proc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "db", nullable = false)
    private String db;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "specific_name", nullable = false)
    private String specificName;

    @Column(name = "language", nullable = false)
    private String language;

    @Column(name = "sql_data_access", nullable = false)
    private String sqlDataAccess;

    @Column(name = "is_deterministic", nullable = false)
    private Boolean deterministic;

    @Column(name = "security_type", nullable = false)
    private String securityType;

    @Column(name = "definer", nullable = false)
    private String definer;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;

    @Column(name = "sql_mode", nullable = false)
    private String sqlMode;

    @Column(name = "character_set_client")
    private String characterSetClient;

    @Column(name = "collation_connection")
    private String collationConnection;

    @Column(name = "db_collation")
    private String dbCollation;

    @Column(name = "param_list", nullable = false)
    private byte[] paramList;

    @Column(name = "returns", nullable = false)
    private byte[] returns;

    @Column(name = "body", nullable = false)
    private byte[] body;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "body_utf8")
    private byte[] bodyUtf8;

}
