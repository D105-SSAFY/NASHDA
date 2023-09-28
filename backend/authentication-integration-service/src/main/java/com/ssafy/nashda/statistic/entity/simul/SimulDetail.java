package com.ssafy.nashda.statistic.entity.simul;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class SimulDetail extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "simul_detail_index")
    private Long index;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "simul_index", nullable = false)
    private SimulStatistic simul;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String my_speech;

    @Column(columnDefinition = "TEXT")
    private String gpt_speech;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_index", nullable = false)
    private SimulType type;

    @Builder
    public SimulDetail(SimulStatistic simulStatistic, String my_speech, String gpt_speech, SimulType type) {
        this.simul = simulStatistic;
        this.my_speech = my_speech;
        this.gpt_speech = gpt_speech;
        this.type = type;
    }
}
