package com.example.population.domain.dto;

import com.example.population.domain.Shape;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ShapeSaveRequestDto {

    private String zIndex;
    private String width;
    private String height;
    private String top;
    private String left;

    @Builder
    public ShapeSaveRequestDto( String zIndex, String width, String height, String top, String left)
    {
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
