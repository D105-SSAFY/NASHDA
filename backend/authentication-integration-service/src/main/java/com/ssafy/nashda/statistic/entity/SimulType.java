package com.ssafy.nashda.statistic.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "simul_type")
@NoArgsConstructor
public class SimulType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "type_index")
    private Long index;

    private String name;

    @Builder
    public SimulType(String name) {
        this.name = name;
    }

}
