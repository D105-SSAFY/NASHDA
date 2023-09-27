package com.ssafy.nashda.statistic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WordStatisticResDto {
    private String letter;
    private String type;
    private int incorrect;
    private int total;

    public WordStatisticResDto(PhonemeInterface phoneme){
        this.letter = phoneme.getLetter();
        switch(phoneme.getType()){
            case 1:
                this.type = "초성";
                break;
            case 2:
                this.type = "중성";
                break;
            case 3:
                this.type = "종성";
                break;
        }
        this.incorrect = phoneme.getIncorrect();
        this.total = phoneme.getTotal();
    }

}
