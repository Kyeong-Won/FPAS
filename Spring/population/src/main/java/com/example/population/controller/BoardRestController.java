package com.example.population.controller;

import com.example.population.domain.Board;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.*;
import com.example.population.service.BoardService;
import com.example.population.service.MemberService;
import com.example.population.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final ShapeService shapeService;

    //도면 저장
    @PostMapping(value = "/board/save")
    public Long boardSave(@ModelAttribute BoardResponseDto boardResponseDto) throws IOException {
        Long memberId = memberService.currentMemberId();
        Long id = boardService.save(memberId, boardResponseDto.getShapes(), boardResponseDto.getFile(), boardResponseDto.getTitles());
        return id;
    }

    //도면 수정(이미지 바뀔 때)
    @PutMapping(value = "/board/change-image/{boardId}/put")
    public Board boardUpdate1(@ModelAttribute BoardUpdate1Dto boardUpdate1Dto, @PathVariable Long boardId) throws IOException {
        List<ShapeUpdateDto> shapes = boardUpdate1Dto.getShapes();
        List<Shape> shapeList = shapeService.update(shapes);

        Board board = boardService.updateById1(boardId, shapeList, boardUpdate1Dto.getFile(), boardUpdate1Dto.getTitles());
        return board;
    }

    //도면 수정(이미지 바뀌지 않을 때)
    @PutMapping(value = "/board/{boardId}/put")
    public Board boardUpdate2(@RequestBody BoardUpdate2Dto boardUpdate2Dto, @PathVariable Long boardId){
        List<ShapeUpdateDto> shapes = boardUpdate2Dto.getShapes();
        List<Shape> shapeList = shapeService.update(shapes);

        Board board = boardService.updateById2(boardId, shapeList, boardUpdate2Dto.getTitles());
        return board;
    }

    @GetMapping(value="/board/camera/{camera_name}")
    public String dashboardGet(@PathVariable String camera_name){
//        String camera_name = dashboardGetDto.getName();
        Shape camera = shapeService.findByName(camera_name);
        return camera.getName();
    }
}
