package com.example.population.controller;

import com.example.population.domain.Board;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.ShapeListDto;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.service.BoardService;
import com.example.population.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    //도면 생성 페이지
    @GetMapping("/boards/board")
    public String board() {
        return "/boards/board";
    }

    //도면 목록 페이지
    @GetMapping("/boards/list")
    public String list(Model model){
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

    //도면 수정 페이지
    @GetMapping("/boards/update/{boardId}")
    public String update(Model model, @PathVariable Long boardId){
        Board board = boardService.findById(boardId);
        List<Shape> shapes = board.getShapes();
        model.addAttribute("shapes", shapes);
        model.addAttribute("title", board.getTitle());
        model.addAttribute("boardId", board.getId());
        return "/boards/boardUpdate";
    }

    //    su 0331
    // 도면 삭제
    @PostMapping("/boards/{boardId}/cancel")
    public String deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:/boards/list";
//        return "boards/boardList";
    }
}
