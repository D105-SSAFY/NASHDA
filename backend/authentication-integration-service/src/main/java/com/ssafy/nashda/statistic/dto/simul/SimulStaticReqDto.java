package com.ssafy.nashda.statistic.dto.simul;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.simul.SimulStatic;
import lombok.*;

import javax.swing.text.html.parser.Entity;

@Getter
@NoArgsConstructor
public class SimulStaticReqDto {
    private Member member;

    @Builder SimulStaticReqDto(SimulStatic simulStatic) {
        this.member = simulStatic.getMember();
    }

    public SimulStatic toEntity(Member member) {
        return SimulStatic.builder()
                .member(member)
                .build();
    }
}
