package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "db")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "db")
public class Db implements Serializable {

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

    @Column(name = "Grant_priv", nullable = false)
    private String grantPriv;

    @Column(name = "References_priv", nullable = false)
    private String referencesPriv;

    @Column(name = "Index_priv", nullable = false)
    private String indexPriv;

    @Column(name = "Alter_priv", nullable = false)
    private String alterPriv;

    @Column(name = "Create_tmp_table_priv", nullable = false)
    private String createTmpTablePriv;

    @Column(name = "Lock_tables_priv", nullable = false)
    private String lockTablesPriv;

    @Column(name = "Create_view_priv", nullable = false)
    private String createViewPriv;

    @Column(name = "Show_view_priv", nullable = false)
    private String showViewPriv;

    @Column(name = "Create_routine_priv", nullable = false)
    private String createRoutinePriv;

    @Column(name = "Alter_routine_priv", nullable = false)
    private String alterRoutinePriv;

    @Column(name = "Execute_priv", nullable = false)
    private String executePriv;

    @Column(name = "Event_priv", nullable = false)
    private String eventPriv;

    @Column(name = "Trigger_priv", nullable = false)
    private String triggerPriv;

}
