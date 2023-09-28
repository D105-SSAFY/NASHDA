package com.ssafy.nashda.statistic.entity;

import com.ssafy.nashda.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_achievement")
@Getter
@Setter
@NoArgsConstructor
public class MemberAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_achievement_index")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_number")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_index")
    private Achievement achievement;

    @CreationTimestamp
    @Column(name = "achieved_date", updatable = false)
    private LocalDateTime achievedDate; // 업적 달성 날짜

    @Builder
    public MemberAchievement(Member member, Achievement achievement) {
        this.member = member;
        this.achievement = achievement;
    }
}
