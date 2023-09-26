package com.ssafy.nashda.statistic.service.practice;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.practice.OnsetStatistic;
import com.ssafy.nashda.statistic.repository.practice.OnsetStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PracticeStatisticServiceImpl implements PracticeStatisticService {
    private final OnsetStatisticRepository onsetStatisticRepository;

    @Override
    public OnsetStatistic findByMemberAndLetter(Member member, String letter) throws Exception{
        Optional<OnsetStatistic> optional = onsetStatisticRepository.findByMemberAndLetter(member, letter);

        // 없는 경우
        if (optional.isEmpty()) {
            if (12592 <= letter.charAt(0) && letter.charAt(0) <= 12687) {
                OnsetStatistic initialOnsetStat = OnsetStatistic.builder()
                        .total(0)
                        .incorrect(0)
                        .letter(letter)
                        .member(member)
                        .build();
                return onsetStatisticRepository.saveAndFlush(initialOnsetStat);
            } else {
                   throw new BadRequestException(ErrorCode.NOT_KOREAN);
            }
        }

        // 있는 경우
        return optional.get();
    }

    @Override
    public OnsetStatistic updateByMemberAndLetter(Member member, String letter) throws Exception {
        return null;
    }
}
