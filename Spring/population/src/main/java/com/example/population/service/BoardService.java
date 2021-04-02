package com.example.population.service;

import com.example.population.domain.Board;
import com.example.population.domain.Camera;
import com.example.population.domain.Member;
import com.example.population.domain.Shape;
import com.example.population.domain.dto.ShapeSaveRequestDto;
import com.example.population.domain.dto.ShapeUpdateDto;
import com.example.population.repository.BoardRepository;
import com.example.population.repository.MemberRepository;
import com.example.population.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ShapeRepository shapeRepository;

    @Transactional
    public Long save(Long memberId, List<ShapeSaveRequestDto> shapeDto, String title) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id="+memberId));

        //camera 생성
        List<Camera> cameraList = new ArrayList<>();

        //shape 생성
        List<Shape> shapeList = new ArrayList<>();
        shapeDto.stream().forEach(shape ->{
            shapeList.add(shape.toEntity());
        });

        //board 생성, 저장
        Board board = Board.createBoard(member, shapeList, cameraList, title);
        boardRepository.save(board);

        return board.getId();
    }

//    @Transactional
//    public List<Shape> load(Long memberId){
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id="+memberId));
//
//        List<Shape> shapeList = member.getBoard().getShapes();
//        return shapeList;
//    }

    @Transactional
    public List<Board> findBoards(){
        return boardRepository.findAll();
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
    public Long updateById(Long boardId, List<ShapeUpdateDto> shapes, String title){
        Board board = findById(boardId);

        for(ShapeUpdateDto shapeDto : shapes){
            Long shapeId = shapeDto.getId();
            Shape shape = shapeRepository.findById(shapeId).orElseThrow(() -> new IllegalArgumentException("해당 도형이 없습니다. id=" + shapeId));

        }
        return boardId;
    }
}
