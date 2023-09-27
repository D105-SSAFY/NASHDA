package com.ssafy.nashda.statistic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "practice_statistic_coda")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodaStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "practice_coda_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    private String letter;

    private int incorrect;

    private int total;

    public void update(boolean isAnswer){
        this.total++;
        if(!isAnswer) this.incorrect++;
    }
}
