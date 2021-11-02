package com.example.population.service;

import com.example.population.domain.Board;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.ShapeListDto;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.domain.dto.ShapeUpdateDto;
import com.example.population.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public List<Shape> update(List<ShapeUpdateDto> shapes){
        List<Shape> shapeList = new ArrayList<>();

        for(ShapeUpdateDto shapeDto : shapes){
            Long shapeId = shapeDto.getId();
            if(shapeId == null) { //기존에 있던 도형이 아닌 새로운 도형일 때
                Shape shape = shapeRepository.save(shapeDto.toEntity());
                shapeList.add(shape);
            }
            else{
                Shape shape = shapeRepository.findById(shapeId).orElseThrow(() -> new IllegalArgumentException("해당 도형이 없습니다. id=" + shapeId));
                shape.updateShape(shapeDto.getZIndex(), shapeDto.getWidth(), shapeDto.getHeight(), shapeDto.getTop(), shapeDto.getLeft());
            }
        }
        return shapeList; //새로운 도형을 보드에 넘겨주기 위해 리턴
    }

    @Transactional
    public Shape findById(Long shapeId){
        return shapeRepository.findById(shapeId).orElseThrow(() -> new IllegalArgumentException("해당 도형이 없습니다. id=" + shapeId));
    }

    @Transactional
    public Shape findByName(String name){
        return shapeRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("해당 도형이 없습니다. name=" + name));
    }


}
