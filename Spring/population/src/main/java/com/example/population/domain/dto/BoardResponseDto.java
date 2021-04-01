package com.example.population.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardResponseDto {
    private List<ShapeSaveRequestDto> shapes;
    private String title;
}
