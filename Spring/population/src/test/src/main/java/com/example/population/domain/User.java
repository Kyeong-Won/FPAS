package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@javax.persistence.Table(name = "user")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Host", nullable = false)
    private String host;

    @Id
    @Column(name = "User", nullable = false)
    private String user;

    @Column(name = "Select_priv", nullable = false)
    private String selectPriv;

    @Column(name = "Insert_priv", nullable = false)
    private String insertPriv;

    @Column(name = "Update_priv", nullable = false)
    private String updatePriv;

    @Column(name = "Delete_priv", nullable = false)
    private String deletePriv;

    @Column(name = "Create_priv", nullable = false)
    private String createPriv;

    @Column(name = "Drop_priv", nullable = false)
    private String dropPriv;

    @Column(name = "Reload_priv", nullable = false)
    private String reloadPriv;

    @Column(name = "Shutdown_priv", nullable = false)
    private String shutdownPriv;

    @Column(name = "Process_priv", nullable = false)
    private String processPriv;

    @Column(name = "File_priv", nullable = false)
    private String filePriv;

    @Column(name = "Grant_priv", nullable = false)
    private String grantPriv;

    @Column(name = "References_priv", nullable = false)
    private String referencesPriv;

    @Column(name = "Index_priv", nullable = false)
    private String indexPriv;

    @Column(name = "Alter_priv", nullable = false)
    private String alterPriv;

    @Column(name = "Show_db_priv", nullable = false)
    private String showDbPriv;

    @Column(name = "Super_priv", nullable = false)
    private String superPriv;

    @Column(name = "Create_tmp_table_priv", nullable = false)
    private String createTmpTablePriv;

    @Column(name = "Lock_tables_priv", nullable = false)
    private String lockTablesPriv;

    @Column(name = "Execute_priv", nullable = false)
    private String executePriv;

    @Column(name = "Repl_slave_priv", nullable = false)
    private String replSlavePriv;

    @Column(name = "Repl_client_priv", nullable = false)
    private String replClientPriv;

    @Column(name = "Create_view_priv", nullable = false)
    private String createViewPriv;

    @Column(name = "Show_view_priv", nullable = false)
    private String showViewPriv;

    @Column(name = "Create_routine_priv", nullable = false)
    private String createRoutinePriv;

    @Column(name = "Alter_routine_priv", nullable = false)
    private String alterRoutinePriv;

    @Column(name = "Create_user_priv", nullable = false)
    private String createUserPriv;

    @Column(name = "Event_priv", nullable = false)
    private String eventPriv;

    @Column(name = "Trigger_priv", nullable = false)
    private String triggerPriv;

    @Column(name = "Create_tablespace_priv", nullable = false)
    private String createTablespacePriv;

    @Column(name = "ssl_type", nullable = false)
    private String sslType;

    @Column(name = "max_questions", nullable = false)
    private Long maxQuestions;

    @Column(name = "max_updates", nullable = false)
    private Long maxUpdates;

    @Column(name = "max_connections", nullable = false)
    private Long maxConnections;

    @Column(name = "max_user_connections", nullable = false)
    private Long maxUserConnections;

    @Column(name = "plugin", nullable = false)
    private String plugin;

    @Column(name = "password_expired", nullable = false)
    private String passwordExpired;

    @Column(name = "password_last_changed")
    private Date passwordLastChanged;

    @Column(name = "password_lifetime")
    private Integer passwordLifetime;

    @Column(name = "account_locked", nullable = false)
    private String accountLocked;

    @Column(name = "ssl_cipher", nullable = false)
    private byte[] sslCipher;

    @Column(name = "x509_issuer", nullable = false)
    private byte[] x509Issuer;

    @Column(name = "x509_subject", nullable = false)
    private byte[] x509Subject;

    @Column(name = "authentication_string")
    private String authenticationString;

}