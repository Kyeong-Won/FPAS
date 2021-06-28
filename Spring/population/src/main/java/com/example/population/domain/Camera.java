package com.example.population.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@Document(indexName="info", aliasName="info-alias" )
@Entity
public class Camera {
    @Id
    @GeneratedValue
    @Column(name = "camera_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_id")
    private DashBoard dashBoard;

    @Field(type = FieldType.Text)
    private String place;

    @Field(type = FieldType.Integer)
    private int count;
    //    private LocalDateTime date;
    @Field(type = FieldType.Date,format = DateFormat.custom, pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private Date date;

    public void init(String place, int count){
//        this.date = LocalDateTime.now();
        this.date = new Date();
        this.place = place;
        this.count = count;
    }
}

