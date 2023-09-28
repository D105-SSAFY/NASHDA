package com.ssafy.nashda.statistic.entity.simul;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "simul_statistic")
@NoArgsConstructor
public class SimulStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "simul_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;

    private Long total;
    private Long correct;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_index", nullable = false)
    private SimulType simulType;

    @Builder
    public SimulStatistic(Member member, Long total, Long correct, SimulType type) {
        this.member = member;
        this.total = 0L;
        this.correct = 0L;
        this.simulType = type;
    }

}
