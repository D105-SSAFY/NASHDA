package com.ssafy.nashda.test.dto.response;

import com.ssafy.nashda.test.entity.BlankTest;
import com.ssafy.nashda.test.entity.SpeedTest1;
import com.ssafy.nashda.test.entity.SpeedTest2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MixTestStartResDto {
    private String index;
    private int try_count;
    private List<BlankTest> blank;
    private List<SpeedTest1> speed1;
    private List<SpeedTest2> speed2;
}
