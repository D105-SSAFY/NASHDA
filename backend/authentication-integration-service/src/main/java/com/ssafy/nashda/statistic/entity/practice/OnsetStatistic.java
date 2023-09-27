package com.ssafy.nashda.statistic.entity.practice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "practice_statistic_onset")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnsetStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "practice_onset_index")
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
