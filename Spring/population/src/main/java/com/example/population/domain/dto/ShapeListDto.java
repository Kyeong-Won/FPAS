package com.example.population.domain.dto;

import com.example.population.domain.Shape;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeListDto {

    private List<Shape> shapeList = new ArrayList<>();

    public void addShape(Shape shape){
        shapeList.add(shape);
    }

}
