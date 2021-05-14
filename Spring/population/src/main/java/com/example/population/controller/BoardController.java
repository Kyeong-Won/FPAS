package com.example.population.controller;

import com.example.population.domain.Board;
import com.example.population.domain.Files;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.ShapeListDto;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.service.BoardService;
import com.example.population.service.FileService;
import com.example.population.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final ShapeService shapeService;
    private final FileService fileService;

    //도면 생성 페이지
    @GetMapping("/boards/board")
    public String board() {
        return "/boards/boardCreate";
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

    //도면 보기 페이지
    @GetMapping("/boards/{boardId}")
    public String showBoard(Model model, @PathVariable Long boardId){
        Board board = boardService.findById(boardId);
        List<Shape> shapes = board.getShapes();
        String img_src = "/boards/image/"+board.getImage().getId();

        model.addAttribute("image", img_src);
        model.addAttribute("shapes", shapes);
        model.addAttribute("title", board.getTitle());
        return "/boards/board";
    }

    @GetMapping(value = "/boards/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> setImageFileById(@PathVariable Long imageId, HttpServletResponse response) throws IOException {
        Files img = fileService.findById(imageId);

        // 파일 정보를 찾고
//        StringBuilder sb = new StringBuilder("file:///c:/tmp/");
        String absolutePath = new File("").getAbsolutePath() + "";

        // 파일이 실제로 저장되어 있는 경로에
        String fileName = img.getFilename();

        // 파일 이름을 더해
        String sb = absolutePath + "/images/" + fileName;
        System.out.println("sb = " + sb);
        InputStream imageStream = new FileInputStream(sb);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream); imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    // 도면 삭제
    @PostMapping("/boards/{boardId}/cancel")
    public String deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:/boards/list";
    }
}
