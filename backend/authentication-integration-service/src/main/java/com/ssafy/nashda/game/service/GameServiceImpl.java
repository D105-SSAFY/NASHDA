package com.ssafy.nashda.game.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.game.dto.request.*;
import com.ssafy.nashda.game.dto.response.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.response.GameSTTResDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetResponseDto;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.statistic.entity.GameStatistic;
import com.ssafy.nashda.statistic.service.GameStatisticService;
import com.ssafy.nashda.stt.service.STTService;
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
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {
    private final ObjectMapper mapper;
    private final MemberService memberService;
    private final WeekService weekService;
    private final STTService sttService;
    private final S3Uploader s3Uploader;
    private final GameStatisticService gameStatisticService;

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
        return (long) (int) response.getBody().getData();
    }

    @Override
    public GameSTTResDto convertSTT(MultipartFile sound, GameSTTReqDto request) throws Exception {
        String answer = request.getAnswer();
        GameSTTResDto gameSTTResDto;
        String stt = sttService.getText(sound);
        if (stt.equals(answer)) {
            gameSTTResDto = new GameSTTResDto(true, stt);
        } else {
            gameSTTResDto = new GameSTTResDto(false, stt);
        }
        return gameSTTResDto;
    }

    @Override
    public void saveSpeedResult(SpeedResultReqDto request, Member member) throws Exception {
        Week week = weekService.getCurrentWeek().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        //판수 증가
        gameStatisticService.updateSpeedSet(member, week);
        //점수 증가
        gameStatisticService.updateSpeedScore(member, week, request.getScore());
        //total증가
        gameStatisticService.updateSpeedTotal(member, week, request.getTotal());
    }

    @Override
    public int saveBlankResult(BlankResultReqDto request, Member member) throws Exception {

        Week week = weekService.getCurrentWeek().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        if (!gameStatisticService.isExistGameStatistic(member, week)) {
            gameStatisticService.initGameStatistic(member, week);
        }
        //판수 증가
        gameStatisticService.updateBlankSet(member, week);
        //점수 증가
        gameStatisticService.updateBlankScore(member, week, request.getScore());
        //total증가
        gameStatisticService.updateBlankTotal(member, week, request.getTotal());

        if (request.getLevel() > 1) {
            memberService.plusProgress(member, request.getTotal());
            return member.getProgress() + request.getTotal();
        }
        return member.getProgress();
    }

    @Override
    public ImgWordSetResponseDto saveImgWordSet(ImgWordSetSaveReqDto imgWordSetSaveReqDto) throws Exception {
        // 이미지 저장
        String imgUrl = s3Uploader.uploadFiles(imgWordSetSaveReqDto.getImg(), "img-word");

        // 문제 서버에 저장 요청
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ImgWordHintSaveDto wordHint = ImgWordHintSaveDto.builder()
                .word(imgWordSetSaveReqDto.getWord())
                .type(imgWordSetSaveReqDto.getType())
                .description(imgWordSetSaveReqDto.getDescription())
                .build();


        InternalImgWordSaveReqDto imgWordSaveReqDto = InternalImgWordSaveReqDto.builder()
                .imgUrl(imgUrl)
                .imgWordHint(wordHint)
                .build();


        ResponseEntity<InternalResponseDto> response = client.post()
                .uri("/game/img-word/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(imgWordSaveReqDto), InternalImgWordSaveReqDto.class)
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

        String status = response.getBody().getStatus();
        if ("400".equals(status)) {
            throw new BadRequestException(ErrorCode.STT_ERROR);
        }

        ImgWordSetResponseDto imgWordSetResponseDto = mapper.convertValue(response.getBody().getData(), ImgWordSetResponseDto.class);


        return imgWordSetResponseDto;

    }

    @Override
    public BlankSetResponseDto saveBlankSet(BlankSetSaveReqDto blankSetSaveReqDto) throws Exception {
        // 이미지 저장
        String imgUrl = s3Uploader.uploadFiles(blankSetSaveReqDto.getImg(), "img-blank");

        // 문제 서버에 저장 요청
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ImgWordHintSaveDto word1 = ImgWordHintSaveDto.builder()
                .word(blankSetSaveReqDto.getWord1())
                .type(blankSetSaveReqDto.getWord1Type())
                .description(blankSetSaveReqDto.getWord1Description())
                .build();
        ImgWordHintSaveDto word2 = ImgWordHintSaveDto.builder()
                .word(blankSetSaveReqDto.getWord2())
                .type(blankSetSaveReqDto.getWord2Type())
                .description(blankSetSaveReqDto.getWord2Description())
                .build();
        ImgWordHintSaveDto word3 = ImgWordHintSaveDto.builder()
                .word(blankSetSaveReqDto.getWord3())
                .type(blankSetSaveReqDto.getWord3Type())
                .description(blankSetSaveReqDto.getWord3Description())
                .build();

        InternalBlankSetSaveReqDto internalBlankSetSaveReqDto = InternalBlankSetSaveReqDto.builder()
                .imgUrl(imgUrl)
                .correctAnswer(blankSetSaveReqDto.getCorrectAnswer())
                .word1(word1)
                .word2(word2)
                .word3(word3)
                .build();

        ResponseEntity<InternalResponseDto> response = client.post()
                .uri("/game/blank/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(internalBlankSetSaveReqDto), InternalBlankSetSaveReqDto.class)
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

        String status = response.getBody().getStatus();
        if ("400".equals(status)) {
            throw new BadRequestException(ErrorCode.STT_ERROR);
        }
        BlankSetResponseDto blankSetResponseDto = mapper.convertValue(response.getBody().getData(), BlankSetResponseDto.class);
        return blankSetResponseDto;

    }
}
