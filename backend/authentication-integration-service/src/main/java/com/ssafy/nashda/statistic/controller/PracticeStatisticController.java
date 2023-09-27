package com.ssafy.nashda.statistic.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.controller.MemberController;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.dto.response.PhonemeInterface;
import com.ssafy.nashda.statistic.service.PracticeStatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic/practice")
public class PracticeStatisticController {
    private final PracticeStatisticService practiceStatisticService;
    private final MemberController memberController;

    @GetMapping("/test1")
    public ResponseEntity<? extends BaseResponseBody> test(@RequestHeader("Authorization") String token) throws Exception{
        Member member = memberController.findMemberByToken(token);

        List<PhonemeInterface> statisticIncorrectPhoneme = practiceStatisticService.getStatisticIncorrectPhoneme(member);
        return ResponseEntity.ok(new BaseResponseBody<>(200, "테스트 성공", statisticIncorrectPhoneme));
    }

    @GetMapping("/word")
    public ResponseEntity<? extends BaseResponseBody> getWordPractice(@RequestHeader("Authorization") String token) throws Exception{
        Member member = memberController.findMemberByToken(token);


        return ResponseEntity.ok(new BaseResponseBody<>(200, "테스트 성공"));
    }
}
