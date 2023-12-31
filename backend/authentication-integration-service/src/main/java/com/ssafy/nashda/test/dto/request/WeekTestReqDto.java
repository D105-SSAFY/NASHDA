package com.ssafy.nashda.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeekTestReqDto {
    private String index;
    private int order;
    private String imgUrl;
}
