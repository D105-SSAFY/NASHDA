package com.ssafy.nashda.statistic.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@Table(name = "achievement")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Achievement {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "achievement_index")
    private Long index;

    @Column(name ="achievement_name")
    private String name;

    @Column(name = "achievement_condition")
    private String condition;

    @Column(name = "achievement_type")
    private String type;

    @Column(name = "achievement_decription")
    private String description;

}
