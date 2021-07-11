package com.example.population.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    //@GeneratedValue(st )
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String username;
    private String password;
    private String birthday;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boards;

    @Builder
    public  Member(Long id, String name, String username, String password, String birthday, String email, String telephone){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.telephone = telephone;
    }

    public void addBoard(Board board){
        boards.add(board);
        board.setMember(this);
    }
}
