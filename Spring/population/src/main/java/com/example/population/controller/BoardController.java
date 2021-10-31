package com.example.population.controller;

import com.example.population.domain.Board;
import com.example.population.domain.Files;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.HeatMapDto;
import com.example.population.domain.dto.ShapeListDto;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.service.BoardService;
import com.example.population.service.ElasticSearchService;
import com.example.population.service.FileService;
import com.example.population.service.ShapeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;
    private final ElasticSearchService elasticSearchService; // add

    //도면 생성 페이지
    @GetMapping("/boards/board")
    public String board(Model model) {
        List<String> boardTitles = new ArrayList<>();
        boardService.findBoards().forEach(board -> boardTitles.add(board.getTitle()));
        model.addAttribute("boardTitles", boardTitles);
        return "boards/boardCreate";
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
        Long image_id = board.getImage().getId();
        List<Shape> shapes = board.getShapes();
        String img_src = "/boards/image/"+image_id;
//
//        List<String> boardTitles = new ArrayList<>();
//        boardService.findBoards().forEach(board_ysu -> boardTitles.add(board_ysu.getTitle()));
//        model.addAttribute("boardTitles", boardTitles);

        model.addAttribute("image", img_src);
        model.addAttribute("shapes", shapes);
        model.addAttribute("title", board.getTitle());
        model.addAttribute("boardId", board.getId());
        model.addAttribute("imageId", image_id);
        return "boards/boardUpdate";
    }

    //도면 보기 페이지
    @GetMapping("/boards/{boardId}")
    public String showBoard(Model model, @PathVariable Long boardId) throws ParseException {
        Board board = boardService.findById(boardId);
        List<Shape> shapes = board.getShapes();
        String img_src = "/boards/image/"+board.getImage().getId();

        Date to = new Date();
        Date from = null;
        Calendar cal = java.util.Calendar.getInstance();
        cal.add(cal.DATE, -14);// 일주일 빼기
        from = cal.getTime();

        ArrayList<HeatMapDto> sumCount = new ArrayList<>();
        int sumValues=0;
        for(Shape s: shapes){
            HeatMapDto dto = new HeatMapDto();
            sumValues=elasticSearchService.count(s.getName(),  from, to);
            dto.setCameraName("heatmap_" + s.getName());
            dto.setSumValue(sumValues);
            sumCount.add(dto);
        }
        log.info("sumCount: {}", sumCount.size());
        log.info("sumCount(1): {}",sumCount.get(0).getSumValue());
        model.addAttribute("sumCount", sumCount);
        model.addAttribute("image", img_src);
        model.addAttribute("shapes", shapes);
        model.addAttribute("title", board.getTitle());
        return "boards/board";
    }

    //이미지 불러오기
    @GetMapping(value = "/boards/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> setImageFileById(@PathVariable Long imageId, HttpServletResponse response) throws IOException {
        Files img = fileService.findById(imageId);

        String absolutePath = new File("").getAbsolutePath() + "";
        String fileName = img.getFilename();
        String sb = absolutePath + "/images/" + fileName;

        InputStream imageStream = new FileInputStream(sb);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    // 도면 삭제
    @PostMapping("/boards/{boardId}/cancel")
    public String deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deleteBoard(boardId);
        return "redirect:boards/list";
    }

}
