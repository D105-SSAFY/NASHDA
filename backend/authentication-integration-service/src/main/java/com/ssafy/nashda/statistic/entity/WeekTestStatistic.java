package com.ssafy.nashda.statistic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.week.entity.Week;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@Table(name = "week_test_statistic")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeekTestStatistic {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "test_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_index", nullable = false)
    private Week week;

    @Column(name = "max_score")
    private int maxScore;

    @Column(name = "test_result_index")
    private String testDetail;

    @Builder
    public WeekTestStatistic(Member member, Week week, String index) {
        this.member = member;
        this.week = week;
        this.testDetail = index;
    }
}
