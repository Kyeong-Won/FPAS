package com.example.population.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@javax.persistence.Table(name = "help_topic")
@javax.persistence.Entity
@lombok.Data
@Data
@Entity
@Table(name = "help_topic")
public class HelpTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "help_topic_id", nullable = false)
    private Long helpTopicId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "help_category_id", nullable = false)
    private Integer helpCategoryId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "example", nullable = false)
    private String example;

    @Column(name = "url", nullable = false)
    private String url;

}
