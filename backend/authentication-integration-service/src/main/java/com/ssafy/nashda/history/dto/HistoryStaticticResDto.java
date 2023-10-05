package com.ssafy.nashda.history.dto;

import com.ssafy.nashda.history.entity.MemberHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HistoryStaticticResDto {
    private int word_count;
    private int sentence_count;
    private int conversation_count;

    @Builder
    public HistoryStaticticResDto(HistoryResDto memberHistory){
        this.word_count = memberHistory.getWord_count();
        this.sentence_count = memberHistory.getSentence_count();
        this.conversation_count = memberHistory.getConversation_count();
    }
}
