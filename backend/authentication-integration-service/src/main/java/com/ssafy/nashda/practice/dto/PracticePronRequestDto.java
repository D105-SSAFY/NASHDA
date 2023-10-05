package com.ssafy.nashda.practice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracticePronRequestDto {
    private long index;
    private String type;
}
