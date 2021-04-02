package com.example.population.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardUpdateDto {
    private List<ShapeUpdateDto> shapes;
    private List<Long> shapesId;
    private String title;
}
