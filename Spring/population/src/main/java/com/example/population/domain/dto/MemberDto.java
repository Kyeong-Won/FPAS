package com.example.population.domain.dto;

import com.example.population.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String birthday;
    private String email;
    private String telephone;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .username(username)
                .password(password)
                .birthday(birthday)
                .email(email)
                .telephone(telephone)
                .build();
    }

    @Builder
    public MemberDto(Long id, String name, String username, String password, String birthday, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.telephone = telephone;
    }
}
