package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import com.ssafy.nashda.practice.repository.PronComplexSetRepository;
import com.ssafy.nashda.practice.repository.PronPhaseSetRepository;
import com.ssafy.nashda.practice.repository.PronSimpleSetRepository;
import com.ssafy.nashda.practice.repository.PronWordSetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticePronServiceImpl implements PracticePronService {

    private final PronWordSetRepository pronWordSetRepository;
    private final PronPhaseSetRepository pronPhaseSetRepository;
    private final PronSimpleSetRepository pronSimpleSetRepository;
    private final PronComplexSetRepository pronComplexSetRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public PronWordSet getPronWordSets(int index) {
        PronWordSet pronWordSet = pronWordSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return pronWordSet;
    }

    @Override
    public PronWordSet savePronWordSet() throws Exception {
        PronWordSet pronWordSet = PronWordSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronWordSet.SEQUENCE_NAME))
                .originSentence("밟다")
                .pronunciation("밥따")
                .domain("일상")
                .build();

        PronWordSet save = pronWordSetRepository.save(pronWordSet);
        return save;
    }

    @Override
    public PronPhaseSet getPronPhaseSets(int index) throws Exception {
        PronPhaseSet pronPhaseSet = pronPhaseSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return pronPhaseSet;
    }

    @Override
    public PronPhaseSet savePronPhaseSet() throws Exception {
        PronPhaseSet pronPhaseSet = PronPhaseSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronPhaseSet.SEQUENCE_NAME))
                .originSentence("꽃을")
                .pronunciation("꼬츨")
                .domain("일상")
                .build();

        PronPhaseSet saved = pronPhaseSetRepository.save(pronPhaseSet);
        return saved;
    }

    @Override
    public PronSimpleSet getPronSimpleSets(int index) throws Exception {
        PronSimpleSet pronSimpleSet = pronSimpleSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return pronSimpleSet;
    }

    @Override
    public PronSimpleSet savePronSimpleSet() throws Exception {
        PronSimpleSet pronSimpleSet = PronSimpleSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronSimpleSet.SEQUENCE_NAME))
                .originSentence("꽃을 밟다")
                .pronunciation("꼬츨 밥따")
                .domain("일상")
                .build();

        PronSimpleSet save = pronSimpleSetRepository.save(pronSimpleSet);

        return save;
    }

    @Override
    public PronComplexSet getPronComplexSets(int index) throws Exception {
        PronComplexSet pronComplexSet = pronComplexSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return pronComplexSet;
    }

    @Override
    public PronComplexSet savePronComplexSet() throws Exception {
        PronComplexSet pronComplexSet = PronComplexSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronComplexSet.SEQUENCE_NAME))
                .originSentence("꽃이 피고 향기가 퍼지는 봄날 나는 공원을 산책하며 행복을 느꼈다")
                .pronunciation("꼬치 피고 향기가 퍼지는 봄날 나는 공원을 산책하며 행복을 느꼈다")
                .domain("일상")
                .build();
        PronComplexSet save = pronComplexSetRepository.save(pronComplexSet);

        return save;
    }

    @Override
    public long getPronSetNum(String seqName) throws Exception {
        long sequenceNum = sequenceGeneratorService.getSequenceNum(seqName);

        return sequenceNum;
    }

    @Override
    public String getSTT(MultipartFile multipartFile, long index, String type) {

        // STT 부분
//        MultipartFile to  File

        String sttResult = "";
        // 통계 저장 부분
//        String originPron;
//        switch(type){
//            case "word":
//                pronWordSetRepository.findByNum(index);
//                break;
//            case "phase":
//                break;
//            case "simple":
//                break;
//            case "complex":
//                break;
//        }

        return sttResult;
    }


}
