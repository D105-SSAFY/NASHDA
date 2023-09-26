package com.ssafy.nashda.statistic.controller.simul;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/practice/simul")
public class SimulStatisticController {

    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> getSimul(@RequestHeader ("Authorization") String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "대화 시뮬레이션 통계 조회 성공"));
    }

    @PostMapping("/{background}")
    public ResponseEntity<? extends BaseResponseBody> getSimulDetail(@PathVariable String background,
                                                                     @RequestHeader ("Authorization") String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "시뮬레이션 상세 통계 조회 성공"));
    }
}
