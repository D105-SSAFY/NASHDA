package com.ssafy.nashda.game.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.week.entity.Week;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@Table(name = "game_static")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameStatistic{

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "game_static_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    @Column(name = "blank_score")
    private int blankScore;

    @Column(name = "speed_score")
    private int speedScore;

    @Column(name = "speed_total")
    private int speedTotal;

    @Column(name = "blank_set")
    private int blankSet;

    @Column(name = "speed_set")
    private int speedSet;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "week_index", nullable = false)
    private Week week;

}
