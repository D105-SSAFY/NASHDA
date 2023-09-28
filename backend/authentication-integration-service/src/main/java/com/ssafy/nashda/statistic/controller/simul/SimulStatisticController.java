package com.ssafy.nashda.statistic.controller.simul;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.simul.SimulDetailResDto;
import com.ssafy.nashda.statistic.dto.simul.SimulResDto;
import com.ssafy.nashda.statistic.entity.simul.SimulDetail;
import com.ssafy.nashda.statistic.entity.simul.SimulStatistic;
import com.ssafy.nashda.statistic.entity.simul.SimulType;
import com.ssafy.nashda.statistic.repository.simul.SimulDetailRepository;
import com.ssafy.nashda.statistic.repository.simul.SimulStaticRepository;
import com.ssafy.nashda.statistic.repository.simul.SimulTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/statistic/practice/simul")
public class SimulStatisticController {

    private final MemberController memberController;
    private final SimulStaticRepository simulStaticRepository;
    private final SimulDetailRepository simulDetailRepository;
    private final SimulTypeRepository simulTypeRepository;

    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> getSimul(@RequestHeader ("Authorization") String accessToken) {
        Member member = memberController.findMemberByToken(accessToken);
        List<SimulType> simulTypeList = simulTypeRepository.findAll();

        List<SimulResDto> result = new ArrayList<>();

        for (SimulType simulType :simulTypeList) {
            Optional<SimulStatistic> optionalSimul = simulStaticRepository.findByMemberAndSimulType(member, simulType);

            if (!optionalSimul.isPresent()) {
                continue;
            }

            SimulStatistic simulStatistic = optionalSimul.get();
            SimulResDto simulResDto = new SimulResDto(simulStatistic, simulType);
            result.add(simulResDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "대화 시뮬레이션 통계 조회 성공", result));
    }

    @PostMapping("/{background}")
    public ResponseEntity<? extends BaseResponseBody> getSimulDetail(@PathVariable String background,
                                                                     @RequestHeader ("Authorization") String accessToken) {
        Member member = memberController.findMemberByToken(accessToken);
        SimulType simulType = simulTypeRepository.findByName(background);
        SimulStatistic simulStatistic = simulStaticRepository.findByMemberAndSimulType(member, simulType).orElseThrow(() -> {
            throw new BadRequestException(ErrorCode.NOT_PLAY_SIMUL_TYPE);
        });

        List<SimulDetail> simulDetailList = simulDetailRepository.findAllBySimulAndType(simulStatistic, simulType);

        if (simulDetailList.isEmpty()) {
            throw new BadRequestException(ErrorCode.NOT_PLAY_SIMUL_TYPE);
        }

        List<SimulDetailResDto> result = new ArrayList<>();

        for (SimulDetail detail : simulDetailList) {

            SimulDetailResDto dto = new SimulDetailResDto(detail);
            result.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "시뮬레이션 상세 통계 조회 성공", result));
    }
}
