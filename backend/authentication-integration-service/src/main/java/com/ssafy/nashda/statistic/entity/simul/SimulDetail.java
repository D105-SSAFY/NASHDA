package com.ssafy.nashda.statistic.entity.simul;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;

import javax.persistence.*;

import com.ssafy.nashda.statistic.entity.simul.SimulStatic;
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
    private SimulStatic simul;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String my_speech;

    @Column(columnDefinition = "TEXT")
    private String gpt_speech;

    private String type;

    @Builder
    public SimulDetail(SimulStatic simulStatic, String my_speech, String gpt_speech, String type) {
        this.simul = simulStatic;
        this.my_speech = my_speech;
        this.gpt_speech = gpt_speech;
        this.type = type;
    }
}
