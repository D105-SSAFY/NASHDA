package com.ssafy.nashda.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class InternalPronNumResponseDto extends InternalResponseDto{
    long data;
}
