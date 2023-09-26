package com.ssafy.nashda.statistic.entity.simul;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;
import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "simul_statistic")
@NoArgsConstructor
public class SimulStatic {
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

    @Builder
    public SimulStatic(Member member, Long total, Long correct) {
        this.member = member;
        this.total = 0L;
        this.correct = 0L;
    }

}
