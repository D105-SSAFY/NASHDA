package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.practice.dto.InternalPronNumResponseDto;
import com.ssafy.nashda.practice.dto.InternalPronResponse;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final TextProcessService textProcessService;
    private static final String DOCKER_ADDRESS = "http://172.17.0.5:";
    private static final String LOCAL_ADDRESS = "http://localhost:";

    @Override
    public PronResponseDto getPronWordSets(int index) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(DOCKER_ADDRESS + "8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalPronResponse> response = client.get()
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
                .toEntity(InternalPronResponse.class)
                .block();

//        log.info("response : {}", response.getBody().getData());
        return response.getBody().getData();
    }


    @Override
    public PronResponseDto getPronPhaseSets(int index) throws Exception {
//        PronPhaseSet pronPhaseSet = pronPhaseSetRepository.findByNum(index)
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        // 문제 서버에 요청
        WebClient client = WebClient.builder()
                .baseUrl(DOCKER_ADDRESS + "8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalPronResponse> response = client.get()
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
                .toEntity(InternalPronResponse.class)
                .block();

        return response.getBody().getData();
    }


    @Override
    public PronResponseDto getPronSimpleSets(int index) throws Exception {
//        PronSimpleSet pronSimpleSet = pronSimpleSetRepository.findByNum(index)
//                .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        WebClient client = WebClient.builder()
                .baseUrl(DOCKER_ADDRESS + "8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalPronResponse> response = client.get()
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
                .toEntity(InternalPronResponse.class)
                .block();
        return response.getBody().getData();
    }


    @Override
    public PronResponseDto getPronComplexSets(int index) throws Exception {
        WebClient client = WebClient.builder()
                .baseUrl(DOCKER_ADDRESS + "8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalPronResponse> response = client.get()
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
                .toEntity(InternalPronResponse.class)
                .block();

        return response.getBody().getData();
    }


    @Override
    public long getPronSetNum(String seqName) throws Exception {
        // 문제 서버에 요청
        WebClient client = WebClient.builder()
                .baseUrl(DOCKER_ADDRESS + "8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalPronNumResponseDto> response = client.get()
                .uri("/practice/pron/nums/" + seqName)
                .retrieve()
                .toEntity(InternalPronNumResponseDto.class)
                .block();
        log.info("response : {}", response.getBody().getData());

        return response.getBody().getData();
    }

    @Override
    public String getSTT(MultipartFile multipartFile, long index, String type) throws Exception {

        // STT 부분
        // MultipartFile to  File
        // FAST API 와 소통하기


        String sttResult = "발따"; // 받아온 STT
        // 통계 저장 부분

        // 1. 해당 문제를 받아온다.
        WebClient client = WebClient.builder()
                .baseUrl(DOCKER_ADDRESS + "8082")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        ResponseEntity<InternalPronResponse> response = client.get()
                .uri("/practice/pron/" + type + "/" + index)
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
                .toEntity(InternalPronResponse.class)
                .block();

        PronResponseDto pronResponse = response.getBody().getData();
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
            if (!onsetOrigin.equals(onsetResult)) { // 틀린 발음의 경우 초성, 중성, 종성으로 분리하여 저장한다.
                String onset = textProcessService.getOnset(origin.charAt(i));
                log.info("초성 오류 !!! : {}", onset);
            }

            // 중성 비교
            String nucleusResult = textProcessService.getNucleus(sttResult.charAt(i));
            String nucleusOrigin = textProcessService.getNucleus(convertOrigin.charAt(i));
            if (!nucleusOrigin.equals(nucleusResult)) { // 틀린 발음의 경우 초성, 중성, 종성으로 분리하여 저장한다.
                String nucleus = textProcessService.getNucleus(origin.charAt(i));
                log.info("중성 오류 !!! : {}", nucleus);
            }

            // 종성 비교
            String codaResult = textProcessService.getCoda(sttResult.charAt(i));
            String codaOrigin = textProcessService.getCoda(convertOrigin.charAt(i));
            if (!codaOrigin.equals(codaResult)) { // 틀린 발음의 경우 초성, 중성, 종성으로 분리하여 저장한다.
                String coda = textProcessService.getCoda(origin.charAt(i));
                log.info("종성 오류 !!! : {}", coda);
            }
        }

        return sttResult;
    }


}

