package com.ssafy.nashda.game.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.sequence.service.SequenceGeneratorService;
import com.ssafy.nashda.game.dto.BlankSetSaveRequestDto;
import com.ssafy.nashda.game.dto.ImgWordSetSaveRequestDto;
import com.ssafy.nashda.game.entity.BlankQuestionSet;
import com.ssafy.nashda.game.entity.ImgWordHint;
import com.ssafy.nashda.game.entity.ImgWordSet;
import com.ssafy.nashda.game.repository.BlankQuestionSetRepository;
import com.ssafy.nashda.game.repository.ImgWordHintRepository;
import com.ssafy.nashda.game.repository.ImgWordSetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final ImgWordSetRepository imgWordSetRepository;
    private final ImgWordHintRepository imgWordHintRepository;
    private final BlankQuestionSetRepository blankQuestionSetRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public ImgWordSet saveImgWordSet(ImgWordSetSaveRequestDto requestDto) throws Exception {
        ImgWordHint imgWordHint = ImgWordHint.builder()
                .word(requestDto.getImgWordHint().getWord())
                .description(requestDto.getImgWordHint().getDescription())
                .type(requestDto.getImgWordHint().getType())
                .build();
        ImgWordHint savedHint = imgWordHintRepository.save(imgWordHint);

        ImgWordSet imgWordSet = ImgWordSet.builder()
                .num(sequenceGeneratorService.generateSequence(ImgWordSet.SEQUENCE_NAME))
                .word(requestDto.getImgWordHint().getWord())
                .imgURL(requestDto.getImgUrl())
                .imgWordHint(savedHint)
                .build();

        ImgWordSet save = imgWordSetRepository.save(imgWordSet);
        return save;
    }

    @Override
    public ImgWordSet getImgWordSet(long index) throws Exception {
        ImgWordSet imgWordSet = imgWordSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        return imgWordSet;
    }

    @Override
    public List<ImgWordSet> getImgWordSetList(long index) throws Exception {

        ImgWordSet imgWordSet = imgWordSetRepository.findByNum(index)
                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));

        List<ImgWordSet> list = imgWordSetRepository.findRandom(index);

        List<ImgWordSet> imgWordSetList = new ArrayList<>();

        imgWordSetList.add(imgWordSet);
        for (ImgWordSet iws : list) {
            imgWordSetList.add(iws);
        }

        return imgWordSetList;
    }

    @Override
    public BlankQuestionSet saveBlankSet(BlankSetSaveRequestDto requestDto) throws Exception {
        ImgWordHint imgWordHint1 = imgWordHintRepository.save(new ImgWordHint(requestDto.getWord1()));
        ImgWordHint imgWordHint2 = imgWordHintRepository.save(new ImgWordHint(requestDto.getWord2()));
        ImgWordHint imgWordHint3 = imgWordHintRepository.save(new ImgWordHint(requestDto.getWord3()));

        BlankQuestionSet blankQuestionSet = BlankQuestionSet.builder()
                .num(sequenceGeneratorService.generateSequence(BlankQuestionSet.SEQUENCE_NAME))
                .correctAnswer(requestDto.getCorrectAnswer())
                .imgURL(requestDto.getImgUrl())
                .word1(imgWordHint1)
                .word2(imgWordHint2)
                .word3(imgWordHint3)
                .build();

        BlankQuestionSet save = blankQuestionSetRepository.save(blankQuestionSet);

        return save;
    }

    @Override
    public List<BlankQuestionSet> getBlankSetList() throws Exception {
        List<BlankQuestionSet> blankQuestionSetList = blankQuestionSetRepository.findRandom();


        return blankQuestionSetList;
    }

    @Override
    public long getGameSetNum(String seqName) {
        return sequenceGeneratorService.getSequenceNum(seqName);
    }

}
