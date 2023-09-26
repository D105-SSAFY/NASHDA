package com.ssafy.nashda.game.dto.response;

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

}
