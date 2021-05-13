package com.example.population.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class BoardResponseDto {
    private List<ShapeSaveRequestDto> shapes;
    private MultipartFile file; //modify
    private String titles;
}
