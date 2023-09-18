package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.practice.entity.PronWordSet;
import com.ssafy.nashda.practice.repository.PronWordSetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticePronServiceImpl implements PracticePronService {

    private final PronWordSetRepository pronunciationWordSetRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    @Override
    public PronWordSet getPronWordSets(int index) {
        PronWordSet pronWordSet = pronunciationWordSetRepository.findByNum(index).orElseThrow(
                () -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA)
        );

        return pronWordSet;
    }

    @Override
    public PronWordSet savePronWordSet() throws Exception{
        PronWordSet pronWord1 = PronWordSet.builder()
                .originSentence("밟다")
                .pronunciation("밥따")
                .domain("학교")
                .num(sequenceGeneratorService.generateSequence(PronWordSet.SEQUENCE_NAME))
                .build();
        log.info("pron Origin {}", pronWord1.getOriginSentence() );
        PronWordSet save = pronunciationWordSetRepository.save(pronWord1);
        return save;
    }


}
