package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.practice.dto.PronSaveRequestDto;
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

import java.util.List;

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
    public PronWordSet savePronWordSet(PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronWordSet pronWordSet = PronWordSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronWordSet.SEQUENCE_NAME))
                .originSentence(pronSaveRequestDto.getOriginSentence())
                .pronunciation(pronSaveRequestDto.getPronunciation())
                .job(pronSaveRequestDto.getJob())
                .hobby(pronSaveRequestDto.getHobby())
                .build();

        PronWordSet save = pronWordSetRepository.save(pronWordSet);
        return save;
    }

    @Override
    public List<PronWordSet> findTestWordSet() throws Exception {
        List<PronWordSet> randomWord = pronWordSetRepository.findRandomWord();

        return randomWord;
    }

    @Override
    public PronPhaseSet getPronPhaseSets(int index) throws Exception {
        PronPhaseSet pronPhaseSet = pronPhaseSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return pronPhaseSet;
    }

    @Override
    public PronPhaseSet savePronPhaseSet(PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronPhaseSet pronPhaseSet = PronPhaseSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronPhaseSet.SEQUENCE_NAME))
                .originSentence(pronSaveRequestDto.getOriginSentence())
                .pronunciation(pronSaveRequestDto.getPronunciation())
                .job(pronSaveRequestDto.getJob())
                .hobby(pronSaveRequestDto.getHobby())
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
    public PronSimpleSet savePronSimpleSet(PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronSimpleSet pronSimpleSet = PronSimpleSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronSimpleSet.SEQUENCE_NAME))
                .originSentence(pronSaveRequestDto.getOriginSentence())
                .pronunciation(pronSaveRequestDto.getPronunciation())
                .job(pronSaveRequestDto.getJob())
                .hobby(pronSaveRequestDto.getHobby())
                .build();

        PronSimpleSet save = pronSimpleSetRepository.save(pronSimpleSet);

        return save;
    }

    @Override
    public List<PronSimpleSet> findTestSimpleSet() throws Exception {
        List<PronSimpleSet> randomSimple = pronSimpleSetRepository.findRandomSimple();
        return randomSimple;
    }

    @Override
    public PronComplexSet getPronComplexSets(int index) throws Exception {
        PronComplexSet pronComplexSet = pronComplexSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return pronComplexSet;
    }

    @Override
    public PronComplexSet savePronComplexSet(PronSaveRequestDto pronSaveRequestDto) throws Exception {
        PronComplexSet pronComplexSet = PronComplexSet.builder()
                .num(sequenceGeneratorService.generateSequence(PronComplexSet.SEQUENCE_NAME))
                .originSentence(pronSaveRequestDto.getOriginSentence())
                .pronunciation(pronSaveRequestDto.getPronunciation())
                .job(pronSaveRequestDto.getJob())
                .hobby(pronSaveRequestDto.getHobby())
                .build();

        PronComplexSet save = pronComplexSetRepository.save(pronComplexSet);

        return save;
    }

    @Override
    public long getPronSetNum(String seqName) throws Exception {
        long sequenceNum = sequenceGeneratorService.getSequenceNum(seqName);

        return sequenceNum;
    }



//    @Override
//    public String getSTT(MultipartFile multipartFile, long index, String type) {
//
//        // STT 부분
////        MultipartFile to  File
//
//        String sttResult = "";
//        // 통계 저장 부분
////        String originPron;
////        switch(type){
////            case "word":
////                pronWordSetRepository.findByNum(index);
////                break;
////            case "phase":
////                break;
////            case "simple":
////                break;
////            case "complex":
////                break;
////        }
//
//        return sttResult;
//    }


}
