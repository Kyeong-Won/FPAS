package com.example.population.controller;

import com.example.population.domain.Shape;
import com.example.population.domain.dto.BoardResponseDto;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;

    //도면 저장
    @PostMapping(value = "/board/save/{memberId}")
    public Long boardSave(@RequestBody BoardResponseDto boardResponseDto, @PathVariable Long memberId){
        Long id = boardService.save(memberId, boardResponseDto.getShapes(), boardResponseDto.getTitle());
        return id;
    }

    //도면 수정 추가


    //도면 삭제하기
    // 1. 해당 도면 id의 shape부터 삭제
    // 2. 그 다음에 해당 도면 삭제
}
