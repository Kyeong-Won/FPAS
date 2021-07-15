package com.example.population.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;


@Getter @Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "board",  cascade = CascadeType.ALL)
    private Files image;

    @OneToMany(mappedBy = "board",  cascade = CascadeType.ALL)
    private List<Shape> shapes = new ArrayList<>();

    private String title;

    //===연관관계 메서드===//
    public void addShape(Shape shape){
        shapes.add(shape);
        shape.setBoard(this);
    }

    public void addFile(Files img){
        this.image = img;
        image.setBoard(this);
    }

    //===생성 메서드===//
    public static Board createBoard(Member member, List<Shape> shapes, Files img, String title){
        Board board = new Board();
        board.setMember(member);

        for(Shape shape : shapes){
            board.addShape(shape);
        }

        board.addFile(img);

        board.setTitle(title);

        return board;
    }


}
