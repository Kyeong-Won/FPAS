package com.example.population.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shape_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String priority;
    private String className;
    private String aria_hidden;
    private String zIndex;
    private String width;
    private String height;
    private String position;
    private String left_;
    private String top;

    private String name; // 카메라 장소
    private String src;
    @Column(columnDefinition = "TEXT")
    private String iframe;

    @Builder
    public Shape(String priority, String className, String aria_hidden, String zIndex, String width,
                 String height, String position, String top, String left, String name, String src, String iframe)
    {
        this.priority = priority;
        this.className = className;
        this.aria_hidden = aria_hidden;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.position = position;
        this.left_ = left;
        this.top = top;
        this.name = name;
        this.src = src;
        this.iframe = iframe;
    }

    public void updateShape(String zIndex, String width, String height, String top, String left)
    {
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.top = top;
        this.left_ = left;
    }

}
