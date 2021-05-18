package com.example.population.domain.dto;

import com.example.population.domain.Shape;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ShapeUpdateDto {
    private Long id;
    private String src;
    private String priority;
    private String className;
    private String aria_hidden;
    private String zIndex;
    private String width;
    private String height;
    private String position;
    private String left;
    private String top;
    private String name;


    @Builder
    public ShapeUpdateDto(Long id, String zIndex, String width, String height, String top, String left,
                          String name, String priority, String className, String aria_hidden, String position, String src)
    {
        this.id = id;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
        this.name = name;
        this.priority = priority;
        this.className = className;
        this.aria_hidden = aria_hidden;
        this.position = position;
        this.src = src;
    }

    public Shape toEntity(){
        return Shape.builder()
                .zIndex(zIndex)
                .width(width)
                .height(height)
                .top(top)
                .left(left)
                .name(name)
                .priority(priority)
                .className(className)
                .aria_hidden(aria_hidden)
                .position(position)
                .src(src)
                .build();
    }
}
