package com.example.population.domain.dto;

import com.example.population.domain.Shape;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShapeUpdateDto {
    private Long id;
    private String zIndex;
    private String width;
    private String height;
    private String top;
    private String left;

    @Builder
    public ShapeUpdateDto(Long id, String zIndex, String width, String height, String top, String left)
    {
        this.id = id;
        this.zIndex = zIndex;
        this.width = width;
        this.height = height;
        this.top = top;
        this.left = left;
    }

    public Shape toEntity(){
        return Shape.builder()
                .zIndex(zIndex)
                .width(width)
                .height(height)
                .top(top)
                .left(left)
                .build();
    }
}
