package com.ssafy.nashda.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.text.service.TextProcessService;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.practice.dto.*;
import com.ssafy.nashda.statistic.service.PracticeStatisticService;
import com.ssafy.nashda.stt.service.STTService;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticePronServiceImpl implements PracticePronService {
    private final ObjectMapper mapper;
    private final TextProcessService textProcessService;
    private final STTService sttService;
    private final PracticeStatisticService practiceStatisticService;
    @Value("${env.PROBLEM_URL}")
    private String URL;

    @Override
    public PronResponseDto getPronWordSets(int index) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/practice/pron/word/" + index)
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


        return mapper.convertValue(response.getBody().getData(), PronResponseDto.class);
    }


    @Override
    public PronResponseDto getPronPhaseSets(int index) throws Exception {
        // 문제 서버에 요청
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/practice/pron/phase/" + index)
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

        return mapper.convertValue(response.getBody().getData(), PronResponseDto.class);
    }


    @Override
    public PronResponseDto getPronSimpleSets(int index) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/practice/pron/simple/" + index)
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
        return mapper.convertValue(response.getBody().getData(), PronResponseDto.class);
    }


    @Override
    public PronResponseDto getPronComplexSets(int index) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/practice/pron/complex/" + index)
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

        return mapper.convertValue(response.getBody().getData(), PronResponseDto.class);
    }


    @Override
    public long getPronSetNum(String seqName) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/practice/pron/nums/" + seqName)
                .retrieve()
                .toEntity(InternalResponseDto.class)
                .block();
        log.info("response : {}, type : {}", response.getBody().getData(), response.getBody().getData().getClass().getName());
        return (long) (int) response.getBody().getData();
    }


    @Override
    public PronSTTResponseDto getPracSTT(Member member, MultipartFile sound, PracticePronRequestDto practicePronRequestDto) throws Exception {

        List<PronImgDto> pronImgDtoList = new ArrayList<>();
        // STT 부분
        // FAST API 와 소통하기
        log.info("name : {}", sound.getOriginalFilename());
        String sttResult = sttService.getPronunciation(sound); // 받아온 STT

        // 1. 해당 문제를 받아온다.
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalResponseDto> response = client.get()
                .uri("/practice/pron/" + practicePronRequestDto.getType() + "/" + practicePronRequestDto.getIndex())
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

        PronResponseDto pronResponse = mapper.convertValue(response.getBody().getData(), PronResponseDto.class);

        String convertOrigin = pronResponse.getConvert(); // 원발음

        String convertOriginTrim = convertOrigin.trim().replaceAll("[\\s!@#$%^&*().]", "");
        String sttTrim = sttResult.trim().replaceAll("[\\s!@#$%^&*().]", "");

        String origin = pronResponse.getOrigin().trim().replaceAll("[\\s!@#$%^&*().]", ""); // 빈칸이 제거된 원문
        log.info("origin : {}", origin);

        // 일치하는 문자열 저장
        String correctSentence = textProcessService.findLCS(convertOriginTrim, sttTrim); // 발음과 원문과 매치되는 문자열
        int compIndex = 0;

        for (int i = 0; i < origin.length(); ++i) {
            String onset = textProcessService.getOnset(origin.charAt(i)); // 초성
            String nucleus = textProcessService.getNucleus(origin.charAt(i)); // 중성
            String coda = textProcessService.getCoda(origin.charAt(i)); // 종성

            // 일치하는 패턴의 길이보다 인덱스가 안크면서
            if (compIndex < correctSentence.length() && convertOriginTrim.charAt(i) == correctSentence.charAt(compIndex)) {
                // 사용자의 발음과 원문의 발음이 일치하는 경우 해당 문자를 정답으로 기록
                log.info("맞는 발음 : {}", origin.charAt(i));
                practiceStatisticService.updateOnsetByMemberAndLetter(member, onset, true);
                practiceStatisticService.updateNucleusByMemberAndLetter(member, nucleus, true);
                practiceStatisticService.updateCodaByMemberAndLetter(member, coda, true);

                compIndex++;
            } else {
                // 발음이 틀린 경우 해당 문자를 오답으로 기록
                log.info("틀린 발음 : {}", origin.charAt(i));
                String convertOnset = textProcessService.getOnset(convertOriginTrim.charAt(i)); // 발음의 초성
                int onsetIndex = textProcessService.getOnsetIndex(convertOriginTrim.charAt(i)); // 발음의 초성 인덱스

                String consonantURL = TextProcessService.CONSONANT[onsetIndex];
                pronImgDtoList.add(new PronImgDto(convertOnset, consonantURL));
                practiceStatisticService.updateOnsetByMemberAndLetter(member, onset, false);

                String convertNucleus = textProcessService.getNucleus(convertOriginTrim.charAt(i));
                int nucleusIndex = textProcessService.getNucleusIndex(convertOriginTrim.charAt(i));

                String vowelURL = TextProcessService.VOWEL[nucleusIndex];
                pronImgDtoList.add(new PronImgDto(convertNucleus, vowelURL));
                practiceStatisticService.updateNucleusByMemberAndLetter(member, nucleus, false);

                String convertCoda = textProcessService.getCoda(convertOriginTrim.charAt(i));
                if (!"".equals(convertCoda)) {
                    int codaIndex = textProcessService.getCodaIndex(convertOriginTrim.charAt(i));

                    String consonantCodaURL = TextProcessService.CONSONANT_CODA[codaIndex];
                    pronImgDtoList.add(new PronImgDto(convertCoda, consonantCodaURL));
                    practiceStatisticService.updateCodaByMemberAndLetter(member, coda, false);
                }
            }
        }


        return new PronSTTResponseDto(sttResult, pronImgDtoList);
    }


}

