package com.ssafy.nashda.statistic.entity;

import com.ssafy.nashda.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_achievements")
@Getter
@Setter
@NoArgsConstructor
public class MemberAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_number")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_index")
    private Achievement achievement;

    @Column(name = "achieved_date")
    private LocalDateTime achievedDate; // 업적 달성 날짜

    public MemberAchievement(Member member, Achievement achievement, LocalDateTime achievedDate) {
        this.member = member;
        this.achievement = achievement;
        this.achievedDate = achievedDate;
    }
}
