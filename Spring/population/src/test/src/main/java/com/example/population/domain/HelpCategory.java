package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "help_category")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "help_category")
public class HelpCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "help_category_id", nullable = false)
    private Integer helpCategoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "parent_category_id")
    private Integer parentCategoryId;

    @Column(name = "url", nullable = false)
    private String url;

}
