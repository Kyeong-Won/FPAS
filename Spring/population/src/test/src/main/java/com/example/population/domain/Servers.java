package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "servers")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "servers")
public class Servers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Server_name", nullable = false)
    private String serverName;

    @Column(name = "Host", nullable = false)
    private String host;

    @Column(name = "Db", nullable = false)
    private String db;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Port", nullable = false)
    private Integer port;

    @Column(name = "Socket", nullable = false)
    private String socket;

    @Column(name = "Wrapper", nullable = false)
    private String wrapper;

    @Column(name = "Owner", nullable = false)
    private String owner;

}
