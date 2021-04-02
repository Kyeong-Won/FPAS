package com.example.population.domain.dto;

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
    private String display;
    private String top;
    private String left;

    @Builder
    public ShapeUpdateDto(Long id, String priority, String className, String zIndex, String width, String height,
                                String fontSize, String display, String top, String left)
    {
        this.id = id;
        this.priority = priority;
        this.className = className;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        this.display = display;
        this.top = top;
        this.left = left;
    }
}
