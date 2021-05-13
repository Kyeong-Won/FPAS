package com.example.population.controller;

import com.example.population.domain.Board;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.BoardResponseDto;
import com.example.population.domain.dto.BoardUpdateDto;
import com.example.population.domain.dto.ShapeUpdateDto;
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
//        System.out.println("boardResponseDto = " + boardResponseDto);
//        return 1L;
        Long memberId = memberService.currentMemberId();
        System.out.println("memberId = " + memberId);
        Long id = boardService.save(memberId, boardResponseDto.getShapes(), boardResponseDto.getFile(), boardResponseDto.getTitles());
        System.out.println("id = " + id);
        return id;
    }

    //도면 수정 추가
    @PutMapping(value = "/board/{boardId}/put")
    public Board boardUpdate(@RequestBody BoardUpdateDto boardUpdateDto, @PathVariable Long boardId){
        List<ShapeUpdateDto> shapes = boardUpdateDto.getShapes();
        List<Shape> shapeList = shapeService.update(shapes);

        Board board = boardService.updateById(boardId, shapeList, boardUpdateDto.getTitle());
        return board;
    }

    //도면 삭제

}
