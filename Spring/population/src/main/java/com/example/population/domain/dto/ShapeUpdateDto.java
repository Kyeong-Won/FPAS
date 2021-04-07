package com.example.population.domain.dto;

import com.example.population.domain.Shape;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShapeUpdateDto {
    private Long id;
    private String priority;
    private String className;
    private String zIndex;
    private String width;
    private String height;
    private String fontSize;
    private String top;
    private String left;

    @Builder
    public ShapeUpdateDto(Long id, String priority, String className, String zIndex, String width, String height,
                                String fontSize, String top, String left)
    {
        this.id = id;
        this.priority = priority;
        this.className = className;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.top = top;
        this.left = left;
    }

    public Shape toEntity(){
        return Shape.builder()
                .priority(priority)
                .className(className)
                .zIndex(zIndex)
                .width(width)
                .height(height)
                .fontSize(fontSize)
                .top(top)
                .left(left)
                .build();
    }
}
