package com.ssafy.nashda.game.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.game.dto.request.*;
import com.ssafy.nashda.game.dto.response.*;
import com.ssafy.nashda.member.entity.Member;

import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.simulGPT.service.ChatGptService;
import com.ssafy.nashda.statistic.service.GameStatisticService;
import com.ssafy.nashda.stt.service.STTService;
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
import reactor.core.publisher.Mono;

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
    public GameSTTResDto convertSTT(MultipartFile sound, GameSTTReqDto request) throws Exception {

        int type = request.getType();   //0 : speed1, 1:speed2, 2:blank
        int index = request.getIndex();  //문제 번호
        String answer = request.getAnswer();
        GameSTTResDto gameSTTResDto;

        // String stt = sttService.getPronunciation(request.getSound());  //사용자의 음성 파일을 STT
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
        GameStatistic gameStatistic = null;
        Week week = weekService.getCurrentWeek().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
       /* Optional<GameStatistic> optionalGameStatistic = gameStatisticRepository.findByMemberAndWeek(member, week);
        if (optionalGameStatistic.isEmpty()) {
            gameStatistic = saveGameStatistic(member, week);
        } else {
            gameStatistic = optionalGameStatistic.get();
        }

        gameStatistic.setSpeedScore(gameStatistic.getSpeedScore() + request.getScore());
        gameStatistic.setSpeedTotal(gameStatistic.getSpeedTotal() + request.getTotal());
        gameStatistic.setSpeedSet(gameStatistic.getSpeedSet() + 1);*/

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
/*        GameStatistic gameStatistic = gameStatisticRepository.findByMemberAndWeek(member,week).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        gameStatistic.setBlankSet(gameStatistic.getBlankSet() + 1);*/

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
           return member.getProgress()+request.getTotal();
        }
        return member.getProgress();
    }

/*
    public GameStatistic saveGameStatistic(Member member, Week week) throws Exception {
        GameStatistic gameStatistic = new GameStatistic(member, week);
        gameStatisticRepository.save(gameStatistic);
        return gameStatistic;
    }
*/

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
                .body(Mono.just(imgWordSaveReqDto),InternalImgWordSaveReqDto.class)
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

        log.info("response : {}, type : {}",response.getBody().getStatus(), response.getBody().getStatus().getClass().getName());
        String status = response.getBody().getStatus();
        if("400".equals(status)){
            throw new BadRequestException(ErrorCode.STT_ERROR);
        }

        ImgWordSetResponseDto imgWordSetResponseDto = mapper.convertValue(response.getBody().getData(), ImgWordSetResponseDto.class);


        return imgWordSetResponseDto;

    }

    @Override
    public BlankSetResponseDto saveBlankSet( BlankSetSaveReqDto blankSetSaveReqDto) throws Exception {


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
        if("400".equals(status)){
            throw new BadRequestException(ErrorCode.STT_ERROR);
        }

        BlankSetResponseDto blankSetResponseDto = mapper.convertValue(response.getBody().getData(), BlankSetResponseDto.class);

        return blankSetResponseDto;

    }
}
