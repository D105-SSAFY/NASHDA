package com.ssafy.nashda.history.dto;

import com.ssafy.nashda.history.entity.MemberHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResDto {

    private int practice_count;
    private int practice_word_count;
    private int practice_sentence_count;
    private int game_count;
    private int game_blank_count;
    private int game_speed_count;

    private int test_count;
    private int test_speed_count;
    private int test_word_count;
    private int test_week_count;
    private int continuous_login_count;
    private int word_count;
    private int sentence_count;
    private int conversation_count;


    @Builder
    public HistoryResDto(MemberHistory memberHistory){
        this.practice_count = memberHistory.getPracticeCount();
        this.practice_word_count = memberHistory.getPracticeWordCount();
        this.practice_sentence_count = memberHistory.getPracticeSentenceCount();
        this.game_count = memberHistory.getGameCount();
        this.game_blank_count = memberHistory.getGameBlankCount();
        this.game_speed_count = memberHistory.getGameSpeedCount();
        this.test_count = memberHistory.getTestCount();
        this.test_speed_count = memberHistory.getTestSentenceCount();
        this.test_word_count = memberHistory.getTestWordCount();
        this.test_week_count = memberHistory.getTestWeekCount();
        this.continuous_login_count = memberHistory.getContinuousLoginCount();
        this.word_count = memberHistory.getWordCount();
        this.sentence_count = memberHistory.getSentenceCount();
        this.conversation_count = memberHistory.getConversationCount();

    }
}
