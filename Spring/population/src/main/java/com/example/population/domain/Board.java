package com.example.population.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Getter @Setter
@Entity
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @OneToMany(mappedBy = "board",  cascade = CascadeType.ALL)
    private List<Camera> cameras = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board",  cascade = CascadeType.ALL)
    private List<Shape> shapes = new ArrayList<>();

    private String title;

    //===연관관계 메서드===//
    public void addShape(Shape shape){
        shapes.add(shape);
        shape.setBoard(this);
    }

    public void addCamera(Camera camera){
        cameras.add(camera);
        camera.setBoard(this);
    }

    //===생성 메서드===//
    public static Board createBoard(Member member, List<Shape> shapes, List<Camera> cameras, String title){
        Board board = new Board();
        board.setMember(member);

        for(Shape shape : shapes){
            board.addShape(shape);
        }

        for(Camera camera : cameras){
            board.addCamera(camera);
        }

        board.setTitle(title);

        return board;
    }


}
