package com.example.population.domain.dto;

import com.example.population.domain.Shape;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShapeSaveRequestDto {
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
    public ShapeSaveRequestDto(String priority, String className, String aria_hidden, String zIndex, String width, String height, String position, String top, String left, String name)
    {
        this.priority = priority;
        this.className = className;
        this.aria_hidden = aria_hidden;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.position = position;
        this.left = left;
        this.top = top;
        this.name = name;
    }

    public Shape toEntity(){
        return Shape.builder()
                .priority(priority)
                .className(className)
                .aria_hidden(aria_hidden)
                .zIndex(zIndex)
                .width(width)
                .height(height)
                .position(position)
                .left(left)
                .top(top)
                .name(name)
                .build();
    }
}
