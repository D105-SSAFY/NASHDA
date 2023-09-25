package com.ssafy.nashda.game.dto;

import com.ssafy.nashda.game.entity.BlankQuestionSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BlankSetResponseDto {
    private String imgURL;
    private List<String> word;
    private List<String[]> hint;
    private String answer;

    public BlankSetResponseDto(BlankQuestionSet blankQuestionSet) {
        this.word = new ArrayList<>();
        this.hint = new ArrayList<>();

        this.imgURL = blankQuestionSet.getImgURL();

        this.word.add(blankQuestionSet.getWord1().getWord());
        this.word.add(blankQuestionSet.getWord2().getWord());
        this.word.add(blankQuestionSet.getWord3().getWord());

        this.hint.add(new String[]{blankQuestionSet.getWord1().getType(), blankQuestionSet.getWord1().getDescription()});
        this.hint.add(new String[]{blankQuestionSet.getWord2().getType(), blankQuestionSet.getWord2().getDescription()});
        this.hint.add(new String[]{blankQuestionSet.getWord3().getType(), blankQuestionSet.getWord3().getDescription()});

        this.answer = blankQuestionSet.getCorrectAnswer();
    }
}
