package com.ssafy.nashda.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalTestReqDto {
    List<String> problem;
    List<String> convert;
}
