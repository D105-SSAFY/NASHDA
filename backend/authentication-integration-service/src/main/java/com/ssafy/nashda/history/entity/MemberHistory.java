package com.ssafy.nashda.history.entity;

import com.ssafy.nashda.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "member_history")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_history_index")
    private Long index;

    @OneToOne
    @JoinColumn(name = "member_number")
    private Member member;

    @Column(name = "word_count")
    private int wordCount;

    @Column(name = "sentence_count")
    private int sentenceCount;

    @Column(name = "conversation_count")
    private int conversationCount;

    @Column(name = "practice_count")
    private int practiceCount;

    @Column(name = "practice_word_count")
    private int practiceWordCount;

    @Column(name = "practice_sentence_count")
    private int practiceSentenceCount;

    @Column(name = "game_count")
    private int gameCount;

    @Column(name = "game_blank_count")
    private int gameBlankCount;

    @Column(name = "game_speed_count")
    private int gameSpeedCount;

    @Column(name = "test_count")
    private int testCount;

    @Column(name = "test_sentence_count")
    private int testSentenceCount;

    @Column(name = "test_word_count")
    private int testWordCount;

    @Column(name = "test_week_count")
    private int testWeekCount;

    @Column(name = "continuous_login_count")
    private int continuousLoginCount;

    @Builder
    public MemberHistory(Member member){
        this.member = member;
        this.practiceCount = 0;
        this.practiceWordCount = 0;
        this.practiceSentenceCount = 0;
        this.gameCount = 0;
        this.gameBlankCount = 0;
        this.gameSpeedCount = 0;
        this.conversationCount = 0;
        this.testCount = 0;
        this.testSentenceCount = 0;
        this.testWordCount = 0;
        this.testWeekCount = 0;
        this.continuousLoginCount = 0;
    }
}
