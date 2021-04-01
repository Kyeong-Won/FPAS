package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "proxies_priv")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "proxies_priv")
public class ProxiesPriv implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Host", nullable = false)
    private String host;

    @Id
    @Column(name = "User", nullable = false)
    private String user;

    @Id
    @Column(name = "Proxied_host", nullable = false)
    private String proxiedHost;

    @Id
    @Column(name = "Proxied_user", nullable = false)
    private String proxiedUser;

    @Column(name = "With_grant", nullable = false)
    private Boolean withGrant;

    @Column(name = "Grantor", nullable = false)
    private String grantor;

    @Column(name = "Timestamp", nullable = false)
    private Date timestamp;

}
