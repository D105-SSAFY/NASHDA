package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.practice.service.TextProcessService;
import com.ssafy.nashda.statistic.entity.CodaStatistic;
import com.ssafy.nashda.statistic.entity.NucleusStatistic;
import com.ssafy.nashda.statistic.entity.OnsetStatistic;
import com.ssafy.nashda.statistic.repository.CodaStatisticRepository;
import com.ssafy.nashda.statistic.repository.NucleusStatisticRepository;
import com.ssafy.nashda.statistic.repository.OnsetStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PracticeStatisticServiceImpl implements PracticeStatisticService {
    private final OnsetStatisticRepository onsetStatisticRepository;
    private final NucleusStatisticRepository nucleusStatisticRepository;
    private final CodaStatisticRepository codaStatisticRepository;

    @Override
    public OnsetStatistic findByMemberAndLetter(Member member, String letter) throws Exception {
        // 한글이 아닌 경우 예외 발생
        if (12592 > letter.charAt(0) || letter.charAt(0) > 12687) {
            throw new BadRequestException(ErrorCode.NOT_KOREAN);
        }

        return onsetStatisticRepository.findByMemberAndLetter(member, letter)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
    }

    @Override
    public OnsetStatistic updateOnsetByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception {
        OnsetStatistic onsetStatistic = onsetStatisticRepository.findByMemberAndLetter(member, letter)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        onsetStatistic.update(isAnswer);


        return onsetStatistic;
    }

    @Override
    public NucleusStatistic updateNucleusByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception {
        NucleusStatistic nucleusStatistic = nucleusStatisticRepository.findByMemberAndLetter(member, letter)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        nucleusStatistic.update(isAnswer);


        return nucleusStatistic;
    }

    @Override
    public CodaStatistic updateCodaByMemberAndLetter(Member member, String letter, boolean isAnswer) throws Exception {
        CodaStatistic codaStatistic = codaStatisticRepository.findByMemberAndLetter(member, letter)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        codaStatistic.update(isAnswer);

        return codaStatistic;
    }

    @Override
    public boolean initializePracticeStatistic(Member member) throws Exception {
        String[] onset = TextProcessService.ONSET;
        String[] nucleus = TextProcessService.NUCLEUS;
        String[] coda = TextProcessService.CODA;

        try {
            // 초성
            for (String str : onset) {
                OnsetStatistic onsetStatistic = OnsetStatistic.builder()
                        .member(member)
                        .letter(str)
                        .total(0)
                        .incorrect(0)
                        .build();
                onsetStatisticRepository.save(onsetStatistic);
            }

            // 중성
            for (String str : nucleus) {
                NucleusStatistic nucleusStatistic = NucleusStatistic.builder()
                        .member(member)
                        .letter(str)
                        .total(0)
                        .incorrect(0)
                        .build();
                nucleusStatisticRepository.save(nucleusStatistic);
            }

            // 종성
            for (String str : coda) {
                CodaStatistic codaStatistic = CodaStatistic.builder()
                        .member(member)
                        .letter(str)
                        .total(0)
                        .incorrect(0)
                        .build();

                codaStatisticRepository.save(codaStatistic);
            }
        } catch (Exception e) {
            throw new BadRequestException(ErrorCode.SAVE_ERROR);
        }

        return true;
    }
}
