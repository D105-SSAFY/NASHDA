package com.ssafy.nashda.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.member.entity.Member;

import com.ssafy.nashda.member.service.MemberService;
import com.ssafy.nashda.simulGPT.dto.response.ChatSttResDto;
import com.ssafy.nashda.simulGPT.service.ChatGptService;
import com.ssafy.nashda.statistic.service.WeekTestStatisticService;
import com.ssafy.nashda.stt.service.STTService;
import com.ssafy.nashda.test.dto.request.*;
import com.ssafy.nashda.test.dto.response.MixTestStartResDto;
import com.ssafy.nashda.test.dto.response.WeekTestResultDetailResDto;
import com.ssafy.nashda.test.dto.response.WeekTestResultAllResDto;
import com.ssafy.nashda.test.dto.response.WordTestStartResDto;

import com.ssafy.nashda.test.entity.*;
import com.ssafy.nashda.test.repository.MixTestResultRepository;
import com.ssafy.nashda.test.repository.SentenceTestResultRepository;
import com.ssafy.nashda.test.repository.WordTestResultRepository;
import com.ssafy.nashda.week.entity.Week;
import com.ssafy.nashda.week.service.WeekService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final S3Uploader s3Uploader;

    @Value("${env.PROBLEM_URL}")
    private String URL;
    private final MongoTemplate mongoTemplate;
    private final WordTestResultRepository wordTestResultRepository;
    private final SentenceTestResultRepository sentenceTestResultRepository;
    private final MixTestResultRepository mixTestResultRepository;
    private final WeekService weekService;
    private final ObjectMapper objectMapper;
    private final WeekTestStatisticService weekTestStatisticService;
    private final MemberService memberService;
    private final STTService sttService;
    private final ChatGptService chatGptService;

    //단어 문제를 불러오고, mongo에 저장
    @Override
    public WordTestStartResDto wordTestStart(Member member) {

        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String, Object> responseMap = client.get()
                .uri("/practice/pron/word/random")
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }
                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        Map<String, Object> dataMap = (Map<String, Object>) responseMap.get("data");
        InternalTestReqDto internalWordTestReqDto = objectMapper.convertValue(dataMap, InternalTestReqDto.class);

        List<String> problem = internalWordTestReqDto.getProblem();
        List<String> convert = internalWordTestReqDto.getConvert();


        Week week = weekService.getCurrentWeek().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        int tryCount = wordTestResultRepository.findByMemberNumberAndWeek(member.getMemberNum(), week.getWeekIdx()).size();


        WordTestResult testResult = WordTestResult.builder()
                .memberNumber(member.getMemberNum())
                .week(week.getWeekIdx())
                .question(problem)
                .pronunciation(convert)
                .build();
        String index = wordTestResultRepository.save(testResult).getId();

        WordTestStartResDto resDto = WordTestStartResDto.builder()
                .try_count(tryCount + 1)
                .index(index)
                .problem(problem)
                .convert(convert)
                .build();

        return resDto;
    }

    @Override
    public void saveWordTestScore(WordTestResultReqDto reqDto, Member member) {

        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));

        Update update = new Update();
        update.set("score", reqDto.getScore());
        update.set("userPron", reqDto.getPron());

        mongoTemplate.updateFirst(query, update, WordTestResult.class);
    }

    @Override
    public WordTestStartResDto sentenceTestStart(Member member) {
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String, Object> responseMap = client.get()
                .uri("/practice/pron/simple/random")
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }
                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        Map<String, Object> dataMap = (Map<String, Object>) responseMap.get("data");
        InternalTestReqDto internalWordTestReqDto = objectMapper.convertValue(dataMap, InternalTestReqDto.class);

        List<String> problem = internalWordTestReqDto.getProblem();
        List<String> convert = internalWordTestReqDto.getConvert();


        Week week = weekService.getCurrentWeek().orElseThrow();
//        int tryCount = sentenceTestResultRepository.findByMemberNumberAndWeek(member.getMemberNum(), week.getWeekIdx()).size();
        int tryCount = sentenceTestResultRepository.countByMemberNumberAndWeek(member.getMemberNum(), week.getWeekIdx());
        SentenceTestResult testResult = SentenceTestResult.builder()
                .memberNumber(member.getMemberNum())
                .week(week.getWeekIdx())
                .question(problem)
                .pronunciation(convert)
                .build();
        String index = sentenceTestResultRepository.save(testResult).getId();

        WordTestStartResDto resDto = WordTestStartResDto.builder()
                .try_count(tryCount+1)
                .index(index)
                .problem(problem)
                .convert(convert)
                .build();

        return resDto;
    }


    @Override
    public void saveSentenceTestScore(SentenceTestReqDto reqDto, Member member) {
        //들어오는 값은 index,

        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));

        Update update = new Update();
        update.set("score", reqDto.getScore());

        mongoTemplate.updateFirst(query, update, SentenceTestResult.class);
    }

    @Override
    public String sttWordTest(MultipartFile sound, WordTestResultSpeakReqDto reqDto) throws Exception {

        //받아온 soundfile을 stt로 변환
        String url = s3Uploader.uploadFiles(sound, "word_test");
        String stt = sttService.getPronunciation(sound);

        //soundfile을 s3에 업로드후 mongodb에 저장
        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        Update update = new Update().set("user_pronunciation_url", url);
        mongoTemplate.updateFirst(query, update, WordTestResult.class);

        update = new Update().set("user_pronunciation", stt);
        mongoTemplate.updateFirst(query, update, WordTestResult.class);


        //변환된 text를 반환

        return stt;
    }

    @Override
    public String sttSentenceTest(MultipartFile sound, SentenceTestSpeakReqDto reqDto) throws Exception {

        String url = s3Uploader.uploadFiles(sound, "sentence_test");

        String stt = sttService.getPronunciation(sound);
        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        Update update = new Update().set("user_pronunciation_url." + (reqDto.getOrder() - 1), url);
        mongoTemplate.updateFirst(query, update, SentenceTestResult.class);

        update = new Update().set("user_pronunciation." + (reqDto.getOrder() - 1), stt);
        mongoTemplate.updateFirst(query, update, SentenceTestResult.class);

        return stt;
    }

    @Override
    public MixTestStartResDto mixTestStart(Member member) {

        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Map<String, Object> blankRes = client.get()
                .uri("/game/blank")
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }
                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        List<BlankTest> blankList = (List<BlankTest>) blankRes.get("data");


        Map<String, Object> speed1 = client.get()
                .uri("/test/speed1/random")
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }
                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        List<SpeedTest1> speed1List = (List<SpeedTest1>) speed1.get("data");

        Map<String, Object> speed2 = client.get()
                .uri("/test/speed2/random")
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }
                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();

        List<SpeedTest2> speed2List = (List<SpeedTest2>) speed2.get("data");
        Week week = weekService.getCurrentWeek().orElseThrow();
        int tryCount = mixTestResultRepository.findByMemberNumberAndWeekOrderByTryCount(member.getMemberNum(), week.getWeekIdx()).size();


        MixTestResult mixTestResult = MixTestResult.builder()
                .memberNumber(member.getMemberNum())
                .week(week.getWeekIdx())
                .tryCount(tryCount+1)
                .blankTest(blankList)
                .speedTest1(speed1List)
                .speedTest2(speed2List)
                .build();

        String index = mongoTemplate.save(mixTestResult).getId();

        MixTestStartResDto mixTestStartResDto = MixTestStartResDto.builder()
                .index(index)
                .try_count(tryCount)
                .blank(blankList)
                .speed1(speed1List)
                .speed2(speed2List)
                .build();

        return mixTestStartResDto;
    }

    @Override
    public String sttMixTest(MultipartFile sound, WeekTestReqDto reqDto, String type) throws Exception {

        //s3에 sound파일을 업로드 한다.
        String url = s3Uploader.uploadFiles(sound, "week_test" + type);

        //받아온 soundfile을 stt로 변환
        ChatSttResDto response = chatGptService.getStt(sound);
        String stt = response.getText();

        //url을 mongodb에 저장
        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        if (type.equals("blank")) {
            Update update = new Update().set("blank_test." + (reqDto.getOrder() - 1) + ".user_stt", stt);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
            update = new Update().set("blank_test." + (reqDto.getOrder() - 1) + ".sound_url", url);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
        } else {
            Update update = new Update().set("speed_test1." + (reqDto.getOrder() - 5) + ".user_stt", stt);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
            update = new Update().set("speed_test1." + (reqDto.getOrder() - 5) + ".sound_url", url);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
        }


        return stt;
    }

    @Override
    public void saveWeekTestSpeed2(WeekTestReqDto reqDto) {
        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        Update update = new Update().set("speed_test2." + (reqDto.getOrder() - 8) + ".user_answer", reqDto.getImgUrl());
        mongoTemplate.updateFirst(query, update, MixTestResult.class);
    }

    @Override
    public void saveWeekTestScore(WeekTestResultReqDto reqDto, Member member) {
        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        Update update = new Update();
        update.set("score", reqDto.getScore());
        mongoTemplate.updateFirst(query, update, MixTestResult.class);
        Week week = weekService.getCurrentWeek().orElseThrow();
        weekTestStatisticService.updateWeekTestResult(member, week, reqDto);
    }

    @Override
    public WeekTestResultAllResDto getAllWordTestResult(Member member) {
        List<MixTestResult> results = mixTestResultRepository.findByMemberNumberOrderByWeekAscTryCountAsc(member.getMemberNum());
        Map<Long, List<Integer>> scoresByWeek = new HashMap<>();

        for (MixTestResult result : results) {
            long week = result.getWeek();
            int score = result.getScore();

            scoresByWeek
                    .computeIfAbsent(week, k -> new ArrayList<>())
                    .add(score);
        }

        return WeekTestResultAllResDto.builder()
                .scores(scoresByWeek)
                .build();
    }

    @Override
    public List<WeekTestResultDetailResDto> getWeekTestResultDetail(Member member, long week) {

        List<MixTestResult> mixTestResult = mixTestResultRepository.findByMemberNumberAndWeekOrderByTryCount(member.getMemberNum(), week);

        List<WeekTestResultDetailResDto> resDtos = new ArrayList<>();
        for (MixTestResult result : mixTestResult) {
            resDtos.add(WeekTestResultDetailResDto.builder()
                    .try_count(result.getTryCount())
                    .score(result.getScore())
                    .blankTest(result.getBlankTest())
                    .speedTest1(result.getSpeedTest1())
                    .speedTest2(result.getSpeedTest2())
                    .build());
        }

        return resDtos;
    }

}
