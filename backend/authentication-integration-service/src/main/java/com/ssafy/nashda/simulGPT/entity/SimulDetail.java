package com.ssafy.nashda.simulGPT.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.nashda.common.entity.TimeEntity;
import com.ssafy.nashda.member.entity.Member;

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
    @Column(updatable = false, name = "simuldeatil_index")
    private Long index;

//    @JsonIgnore
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "simul_index", nullable = false)
//    private Simul simul;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String my_speech;

    @Column(columnDefinition = "TEXT")
    private String gpt_speech;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Builder
    public SimulDetail(String my_speech) {
        this.my_speech = my_speech;
    }
}
