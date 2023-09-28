package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.SimulDetail;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class SimulDetailResDto {

    private String my_answer;
    private String ai_response;

    public SimulDetailResDto(SimulDetail simulDetail) {
        this.my_answer = simulDetail.getMy_speech();
        this.ai_response = simulDetail.getGpt_speech();
    }
}
