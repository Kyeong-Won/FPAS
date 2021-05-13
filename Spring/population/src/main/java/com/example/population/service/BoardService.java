package com.example.population.service;

import com.example.population.domain.*;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.domain.dto.ShapeUpdateDto;
import com.example.population.repository.BoardRepository;
import com.example.population.repository.MemberRepository;
import com.example.population.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final FileService fileService;

    @Transactional
    public Long save(Long memberId, List<ShapeSaveRequestDto> shapeDto, MultipartFile file, String title) throws IOException {

        //Member 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id="+memberId));

        //camera 생성
//        List<Camera> cameraList = new ArrayList<>();

        //shape 생성
        List<Shape> shapeList = new ArrayList<>();
//        shapeDto.stream().forEach(shape ->{
//            shapeList.add(shape.toEntity());
//        });

        System.out.println("one");
        //File 생성
        Files image = fileService.save(file);


        System.out.println("three");
        //board 생성, 저장
        Board board = Board.createBoard(member, shapeList, image, title);
        System.out.println("six");
        boardRepository.save(board);
        System.out.println("seven");
        System.out.println("eight"+board.getId());
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

    //su 0331
    @Transactional
    public void deleteBoard(Long boardId){
        Board board = findById(boardId);
        boardRepository.delete(board);
    }

    @Transactional
    public Board updateById(Long boardId, List<Shape> shapeList, String title){
        Board board = findById(boardId);

        board.setTitle(title);

        for(Shape shape : shapeList){
            board.addShape(shape);
        }

        return board;
    }


}
