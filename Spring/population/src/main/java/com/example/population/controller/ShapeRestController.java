package com.example.population.controller;

import com.example.population.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ShapeRestController {

    private final ShapeService shapeService;

    //도형 삭제하기
    @DeleteMapping("/board/delete/{shapeId}")
    public Long deleteShape(@PathVariable Long shapeId){
        shapeService.delete(shapeId);
        return shapeId;
    }

}
