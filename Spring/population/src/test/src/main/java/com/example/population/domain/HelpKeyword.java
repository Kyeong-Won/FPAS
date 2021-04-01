package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "help_keyword")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "help_keyword")
public class HelpKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "help_keyword_id", nullable = false)
    private Long helpKeywordId;

    @Column(name = "name", nullable = false)
    private String name;

}
