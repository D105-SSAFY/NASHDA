package com.ssafy.nashda.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeekTestResultDetailReqDto {
    long week;
    int try_count;
}
