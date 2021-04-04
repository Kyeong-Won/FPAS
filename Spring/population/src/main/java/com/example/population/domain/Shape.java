package com.example.population.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Shape {

    //    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(name = "shape_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "board_id")
    private Board board;

    private String priority;
    private String className;
    private String zIndex;
    private String width;
    private String height;
    private String fontSize;
    private String display;
    private String top;
    private String left_;

    @Builder
    public Shape( String priority, String className, String zIndex, String width, String height,
                  String fontSize, String display, String top, String left)
    {
        this.priority = priority;
        this.className = className;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.display = display;
        this.top = top;
        this.left_ = left;
    }

    public void updateShape( String priority, String className, String zIndex, String width, String height,
                        String fontSize, String display, String top, String left)
    {
        this.priority = priority;
        this.className = className;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.display = display;
        this.top = top;
        this.left_ = left;
    }

}
