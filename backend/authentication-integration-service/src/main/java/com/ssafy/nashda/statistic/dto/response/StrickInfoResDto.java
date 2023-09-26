package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.Strick;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//전체 스트릭 정보
@Getter
@Setter
public class StrickInfoResDto {
    private LocalDate date;
    private int game_count;
    private int practice_count;
    private int simulation_count;
    private int word_count;
    private int sentence_count;
    private int conversation_count;
    @Builder
    public StrickInfoResDto(Strick strick) {
        this.date = strick.getCreatOn();
        this.game_count = strick.getGameCount();
        this.practice_count = strick.getPracticeCount();
        this.simulation_count = strick.getSimulationCount();
        this.word_count = strick.getWordCount();
        this.sentence_count = strick.getSentenceCount();
        this.conversation_count = strick.getConversationCount();
    }

}
