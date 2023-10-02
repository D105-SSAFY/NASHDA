package com.ssafy.nashda.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.common.text.service.TextProcessService;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.statistic.service.AchievementService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class PracticePronServiceImpl implements PracticePronService {
    private final ObjectMapper mapper;
    private final TextProcessService textProcessService;
    private final S3Uploader s3Uploader;
    private final STTService sttService;
    private final PracticeStatisticService practiceStatisticService;
    private final MemberService memberService;
    private final AchievementService achievementService;
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

/*    @Override
    @Transactional
    public void updateWordCount(Member member) throws Exception {
        memberService.updateWordCount(member, 1);
        achievementService.updateMemberAchievement(member, "word", member.getWordCount() + 1);
    }

    @Override
    @Transactional
    public void updateSentenceCount(Member member) throws Exception {
        memberService.updateSentenceCount(member, 1);
        achievementService.updateMemberAchievement(member, "sentence", member.getSentenceCount() + 1);
    }*/

    @Override
    public String getPracSTT(Member member, MultipartFile sound, PracticePronRequestDto practicePronRequestDto) throws Exception {

        // STT 부분
        // FAST API 와 소통하기
        log.info("name : {}", sound.getOriginalFilename());
//        String sttResult = sttService.getPronunciation(sound); // 받아온 STT
        String sttResult = "강벼네서 자언어을 탁오 읻읍니다"; // 받아온 STT
//        "강벼네서 자전거를 타고 읻씀니다."

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

        PronResponseDto pronResponse = mapper.convertValue(response.getBody().getData(), PronResponseDto.class);

        String convertOrigin = pronResponse.getConvert(); // 원발음
        String origin = pronResponse.getOrigin().trim().replaceAll("[\\s!@#$%^&*().]", ""); // 빈칸이 제거된 원문
        log.info("origin : {}", origin);


        // 0 : 공통 문자열 시작 인덱스, 1: 공통 문자열 끝 인덱스
        int[] incorrectStringIndex = textProcessService.findIncorrectString(convertOrigin, sttResult);
        log.info("start index : {} ,  end index : {}", incorrectStringIndex[0], incorrectStringIndex[1]);
        for (int i = 0; i < origin.length(); ++i) {
//            log.info("origin.charAt() : {}", origin.charAt(i));
            String onset = textProcessService.getOnset(origin.charAt(i)); // 초성
            String nucleus = textProcessService.getNucleus(origin.charAt(i)); // 중성
            String coda = textProcessService.getCoda(origin.charAt(i)); // 종성

            if (incorrectStringIndex[0] <= i && i <= incorrectStringIndex[1]) {
//                log.info("맞는 발음 : {}", origin.charAt(i));
                // 맞는 발음인 경우
                practiceStatisticService.updateOnsetByMemberAndLetter(member, onset, true);
                practiceStatisticService.updateNucleusByMemberAndLetter(member, nucleus, true);
                practiceStatisticService.updateCodaByMemberAndLetter(member, coda, true);


            } else {
//                log.info("틀린 발음 : {}", origin.charAt(i));
                // 틀린 발음의 경우
                practiceStatisticService.updateOnsetByMemberAndLetter(member, onset, false);
                practiceStatisticService.updateNucleusByMemberAndLetter(member, nucleus, false);
                practiceStatisticService.updateCodaByMemberAndLetter(member, coda, false);
            }

        }

        return sttResult;
    }


}

