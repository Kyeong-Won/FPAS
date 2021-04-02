package com.example.population.controller;

import com.example.population.domain.dto.BoardResponseDto;
import com.example.population.domain.dto.BoardUpdateDto;
import com.example.population.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping(value = "/board/{boardId}/put")
    public Long boardUpdate(@RequestBody BoardUpdateDto boardUpdateDto, @PathVariable Long boardId){
        Long id = boardService.updateById(boardId, boardUpdateDto.getShapes(), boardUpdateDto.getTitle());
        return id;
    }

}
