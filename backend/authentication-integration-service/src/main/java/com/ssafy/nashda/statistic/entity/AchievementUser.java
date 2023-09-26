package com.ssafy.nashda.statistic.entity;

import com.ssafy.nashda.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity // 객체와 테이블 매핑
@Table(name = "achievement_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AchievementUser {
    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정
    @Column(updatable = false, name = "achievement_user_index")
    private Long index;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_index", nullable = false)
    @Column(name = "achievement_index")
    List<Achievement> achievementList;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_number", nullable = false)
    private Member member;


}
