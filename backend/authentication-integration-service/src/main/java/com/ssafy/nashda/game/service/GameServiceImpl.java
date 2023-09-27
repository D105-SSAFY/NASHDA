package com.ssafy.nashda.game.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.game.dto.request.BlankResultReqDto;
import com.ssafy.nashda.game.dto.request.GameSTTReqDto;
import com.ssafy.nashda.game.dto.request.SpeedResultReqDto;
import com.ssafy.nashda.game.dto.response.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.response.GmaeSTTResDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetResponseDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.repository.MemberRepository;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.statistic.repository.GameStatisticRepository;
import com.ssafy.nashda.week.entity.Week;
import com.ssafy.nashda.week.service.WeekService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {
    private final ObjectMapper mapper;
    private final GameStatisticRepository gameStatisticRepository;
    private final MemberRepository memberRepository;
    private final WeekService weekService;

    @Value("${env.PROBLEM_URL}")
    private String URL;

    @Override
    public ImgWordSetResponseDto getImgWordSet(long index) throws Exception {

        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/game/speed/1/" + index)
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            // 에러 코드 별로 예외 처리 가능
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }

                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .toEntity(InternalResponseDto.class)
                .block();

        log.info("response : {}, type : {}", response.getBody().getData(), response.getBody().getData().getClass().getName());

        return mapper.convertValue(response.getBody().getData(), ImgWordSetResponseDto.class);
    }

    @Override
    public ImgWordSetListResponseDto getImgWordSetList(long index) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/game/speed/2/" + index)
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            // 에러 코드 별로 예외 처리 가능
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }

                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .toEntity(InternalResponseDto.class)
                .block();

        log.info("response : {}, type : {}", response.getBody().getData(), response.getBody().getData().getClass().getName());


        return mapper.convertValue(response.getBody().getData(), ImgWordSetListResponseDto.class);


    }

    @Override
    public List<BlankSetResponseDto> getBlankSetList() throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/game/blank")
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            // 에러 코드 별로 예외 처리 가능
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }

                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .toEntity(InternalResponseDto.class)
                .block();

        log.info("response : {}, type : {}", response.getBody().getData(), response.getBody().getData().getClass().getName());
        List<BlankSetResponseDto> blankSetResponseDtoList = new ArrayList<>();

        for (LinkedHashMap linkedHashMap : (List<LinkedHashMap>) response.getBody().getData()) {
            blankSetResponseDtoList.add(mapper.convertValue(linkedHashMap, BlankSetResponseDto.class));
        }

        return blankSetResponseDtoList;
    }

    @Override
    public long getSpeedSetNum() {
        // 문제 서버에 요청
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/game/speed")
                .retrieve()
                .toEntity(InternalResponseDto.class)
                .block();
        log.info("response : {}, type : {}", response.getBody().getData(), response.getBody().getData().getClass().getName());

        return (long) (int) response.getBody().getData();
    }

    @Override
    public GmaeSTTResDto convertSTT(GameSTTReqDto request) throws Exception {

        int type = request.getType();   //0 : speed1, 1:speed2, 2:blank
        int index = request.getIndex();  //문제 번호
        String answer = request.getAnswer();
        GmaeSTTResDto gmaeSTTResDto;
        MultipartFile file = request.getSound();

        String stt = "싸과";  //사용자의 음성 파일을 STT
        if (stt.equals(answer)) {
            gmaeSTTResDto = new GmaeSTTResDto(true, stt);
        } else {
            gmaeSTTResDto = new GmaeSTTResDto(false, stt);
        }

        return gmaeSTTResDto;
    }

    @Override
    public void saveSpeedResult(SpeedResultReqDto request, Member member) throws Exception {
        GameStatistic gameStatistic = null;
        Week week = weekService.getCurrentWeekIdx().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        Optional<GameStatistic> optionalGameStatistic = gameStatisticRepository.findByMemberAndWeek(member, week);
        if (optionalGameStatistic.isEmpty()) {
            gameStatistic = saveGameStatistic(member, week);
        } else {
            gameStatistic = optionalGameStatistic.get();
        }

        gameStatistic.setSpeedScore(gameStatistic.getSpeedScore() + request.getScore());
        gameStatistic.setSpeedTotal(gameStatistic.getSpeedTotal() + request.getTotal());
        gameStatistic.setSpeedSet(gameStatistic.getSpeedSet() + 1);

        //member의 word_count증가
        member.setWordCount(member.getWordCount() + request.getTotal());
        memberRepository.save(member);

        gameStatisticRepository.save(gameStatistic);
    }

    @Override
    public void saveBlankResult(BlankResultReqDto request, Member member) throws Exception {
        GameStatistic gameStatistic = null;

        Week week = weekService.getCurrentWeekIdx().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        Optional<GameStatistic> optionalGameStatistic = gameStatisticRepository.findByMemberAndWeek(member, week);
        if (optionalGameStatistic.isEmpty()) {
            gameStatistic = saveGameStatistic(member, week);
        } else {
            gameStatistic = optionalGameStatistic.get();
        }

        gameStatistic.setBlankScore(gameStatistic.getBlankScore() + request.getScore());
        gameStatistic.setBlankSet(gameStatistic.getBlankSet() + 1);

        //level 2일떄만 progress update
        member.setSentenceCount(member.getSentenceCount() + request.getTotal());
        if (request.getLevel() > 1) {
            member.setProgress(member.getProgress() + request.getScore());
        }
        memberRepository.save(member);

        gameStatisticRepository.save(gameStatistic);
    }

    public GameStatistic saveGameStatistic(Member member, Week week) throws Exception {

        GameStatistic gameStatistic = new GameStatistic(member, week);

        gameStatisticRepository.save(gameStatistic);
        return gameStatistic;
    }
}
