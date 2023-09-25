package com.ssafy.nashda.test.service;

import com.ssafy.nashda.common.sequence.service.SequenceGeneratorService;
import com.ssafy.nashda.game.dto.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.entity.ImgWordHint;
import com.ssafy.nashda.game.entity.ImgWordSet;
import com.ssafy.nashda.game.repository.ImgWordHintRepository;
import com.ssafy.nashda.game.repository.ImgWordSetRepository;
import com.ssafy.nashda.game.service.GameService;
import com.ssafy.nashda.test.dto.response.Speed2TestResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final ImgWordSetRepository imgWordSetRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final GameService gameService;

    @Override
    public List<ImgWordSet> getSpeed1Sets() throws Exception {

        List<ImgWordSet> sets = imgWordSetRepository.getRandom();
        return sets;
    }


    //0부터 num까지 숫자 3개 랜덤 뽑기 함수
    public List<Integer> generateRandomNum() {
        long num = gameService.getGameSetNum(ImgWordSet.SEQUENCE_NAME);

        Set<Integer> randomNumSet = new HashSet<>();
        while (randomNumSet.size() < 3) {
            long random = (long) (Math.random() * num);
            randomNumSet.add((int) random);
        }
        return new ArrayList<>(randomNumSet);
    }
}
