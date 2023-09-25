package com.ssafy.nashda.test.dto.response;

import com.ssafy.nashda.test.entity.BlankTest;
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
    private List<InternalBlankTestResDto> blank;
    private List<InternalSpeedTest1ResDto> seepd1;
    private List<InternalSpeedTest2ResDto> speed2;
}
