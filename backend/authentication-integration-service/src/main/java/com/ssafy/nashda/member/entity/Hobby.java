package com.ssafy.nashda.member.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincreasement 설정
    @Column(name = "hobby_index")
    private Long hobbyIdx;

    @NotNull
    private String hobby;
}
