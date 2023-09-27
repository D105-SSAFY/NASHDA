package com.ssafy.nashda.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.statistic.service.practice.PracticeStatisticService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticePronServiceImpl implements PracticePronService {
    private final ObjectMapper mapper;
    private final TextProcessService textProcessService;
    private final S3Uploader s3Uploader;
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
//        PronPhaseSet pronPhaseSet = pronPhaseSetRepository.findByNum(index)
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
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
//        PronSimpleSet pronSimpleSet = pronSimpleSetRepository.findByNum(index)
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
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
        // 문제 서버에 요청
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
    public String getSTT(Member member, PracticePronRequestDto practicePronRequestDto) throws Exception {

        // STT 부분
        // FAST API 와 소통하기
        String sttResult = sttService.getPronunciation(practicePronRequestDto.getSound()); // 받아온 STT

        // 통계 저장 부분

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

        PronResponseDto pronResponse =  mapper.convertValue(response.getBody().getData(), PronResponseDto.class);
        String convertOrigin = pronResponse.getConvert(); // 원발음

        String origin = pronResponse.getOrigin(); // 원문

        // 2. STT와 발음을 비교 하여 틀린 부분을 찾는다.
        // 1. STT의 발음 길이가 원문의 길이보다 긴 경우
        // 만약 앞에 헛소리해서 긴 경우 -> 다시 요청하기

        // 2. STT의 발음 길이가 원문의 길이보다 짧은 경우
        // 만약 앞에 소리가 짤렸다면? -> 다시 요청하기

        // 3. 길이가 같은 경우
        for (int i = 0; i < convertOrigin.length(); ++i) {
            // 초성 비교
            String onsetResult = textProcessService.getOnset(sttResult.charAt(i));
            String onsetOrigin = textProcessService.getOnset(convertOrigin.charAt(i));
            String onset = textProcessService.getOnset(origin.charAt(i));
            if (!onsetOrigin.equals(onsetResult)) { // 틀린 발음의 경우 초성, 중성, 종성으로 분리하여 저장한다.
                practiceStatisticService.updateOnsetByMemberAndLetter(member, onset, false);
                log.info("초성 오류 !!! : {}", onset);
            } else { // 맞는 경우
                practiceStatisticService.updateOnsetByMemberAndLetter(member, onset, true);
            }

            // 중성 비교
            String nucleusResult = textProcessService.getNucleus(sttResult.charAt(i));
            String nucleusOrigin = textProcessService.getNucleus(convertOrigin.charAt(i));
            String nucleus = textProcessService.getNucleus(origin.charAt(i));
            if (!nucleusOrigin.equals(nucleusResult)) { // 틀린 발음의 경우 초성, 중성, 종성으로 분리하여 저장한다.
                practiceStatisticService.updateNucleusByMemberAndLetter(member, nucleus, false);
                log.info("중성 오류 !!! : {}", nucleus);
            } else{ // 맞는 경우
                practiceStatisticService.updateNucleusByMemberAndLetter(member, nucleus, true);
            }

            // 종성 비교
            String codaResult = textProcessService.getCoda(sttResult.charAt(i));
            String codaOrigin = textProcessService.getCoda(convertOrigin.charAt(i));
            String coda = textProcessService.getCoda(origin.charAt(i));
            if (!codaOrigin.equals(codaResult)) { // 틀린 발음의 경우 초성, 중성, 종성으로 분리하여 저장한다.
                practiceStatisticService.updateCodaByMemberAndLetter(member, coda, false);
                log.info("종성 오류 !!! : {}", coda);
            } else { // 맞는 경우
                practiceStatisticService.updateCodaByMemberAndLetter(member, coda, true);
            }
        }

        return sttResult;
    }


}

