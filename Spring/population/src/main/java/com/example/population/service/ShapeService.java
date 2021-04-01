package com.example.population.service;

import com.example.population.domain.Shape;
import com.example.population.domain.dto.ShapeListDto;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShapeService {

    private final ShapeRepository shapeRepository;

    @Transactional
    public void save(ShapeSaveRequestDto dto){
        shapeRepository.save(dto.toEntity());
    }

    @Transactional
    public void delete(Long id){
        Shape shape = shapeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 도형이 없습니다. id=" + id));

        shapeRepository.delete(shape);
    }
}
