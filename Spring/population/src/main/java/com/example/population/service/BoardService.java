package com.example.population.service;

import com.example.population.domain.*;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.domain.dto.ShapeUpdateDto;
import com.example.population.repository.BoardRepository;
import com.example.population.repository.MemberRepository;
import com.example.population.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class BoardService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final FileService fileService;

    @Transactional
    public Long save(Long memberId, List<ShapeSaveRequestDto> shapeDto, MultipartFile file, String title) throws IOException {

        //Member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id="+memberId));

        //shape 생성
        List<Shape> shapeList = new ArrayList<>();
        shapeDto.stream().forEach(shape ->{
            shapeList.add(shape.toEntity());
        });

        //File 생성
        Files image = fileService.toFileEntity(file);

        //board 생성, 저장
        Board board = Board.createBoard(member, shapeList, image, title);
        boardRepository.save(board);

        return board.getId();
    }

    @Transactional
    public List<Board> findBoards(){
        List<Board> boardList = boardRepository.findAll();
        List<Board> memberBoardList = new ArrayList<>();

        Long memberId = memberService.currentMemberId();
        boardList.stream().forEach(board ->{
            if(board.getMember().getId() == memberId)
                memberBoardList.add(board);
        });

        return memberBoardList;
    }

    @Transactional
    public Board findById(Long boardId){
        return boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("해당 도면이 없습니다. id=" + boardId));
    }

    //ysu 1102
    @Transactional
    public void deleteBoard(Long boardId){
        Board board = findById(boardId);
        Files img = board.getImage();

        String absolutePath = new File("").getAbsolutePath() + "";
        // 파일이 실제로 저장되어 있는 경로에
        String fileName = img.getFilename();
        // 파일 이름을 더해
        String sb = absolutePath + "/images/" + fileName;
        log.info("sb = {}",sb);
        File deleteFile = new File(sb);
        // 파일이 존재하는지 체크 존재할경우 true, 존재하지않을경우 false
        if(deleteFile.exists()) {
            // 파일을 삭제합니다.
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
        boardRepository.delete(board);
    }

    @Transactional
    public Board updateById1(Long boardId, List<Shape> shapeList, MultipartFile file, String title) throws IOException {
        Board board = findById(boardId);
        board.setTitle(title);
        for(Shape shape : shapeList){
            board.addShape(shape);
        }
        Files image = fileService.toFileEntity(file);
        board.addFile(image);
        return board;
    }

    @Transactional
    public Board updateById2(Long boardId, List<Shape> shapeList, String title){
        Board board = findById(boardId);
        board.setTitle(title);
        for(Shape shape : shapeList){
            board.addShape(shape);
        }

        return board;
    }
}
