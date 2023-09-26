package com.ssafy.nashda.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import com.ssafy.nashda.common.s3.S3Uploader;
import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.test.dto.request.InternalTestReqDto;
import com.ssafy.nashda.test.dto.request.MixTestSpeekReqDto;
import com.ssafy.nashda.test.dto.request.SentenceTestSpeakReqDto;
import com.ssafy.nashda.test.dto.request.WordTestResultReqDto;
import com.ssafy.nashda.test.dto.response.*;
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

import java.io.IOException;
import java.util.ArrayList;
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


        Week week = weekService.getCurrentWeekIdx().orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_DATA));
        int tryCount = wordTestResultRepository.findByMemberNumberAndWeek(member.getMemberNum(), week.getWeekIdx()).size();


        WordTestResult testResult = WordTestResult.builder()
                .memberNumber(member.getMemberNum())
                .week(week.getWeekIdx())
                .question(problem)
                .pronunciation(convert)
                .build();
        String index = wordTestResultRepository.save(testResult).getId();

        WordTestStartResDto resDto = WordTestStartResDto.builder()
                .try_count(tryCount)
                .index(index)
                .problem(problem)
                .convert(convert)
                .build();

        return resDto;
    }

    @Override
    public void saveWordTestScore(WordTestResultReqDto reqDto) {

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


        Week week = weekService.getCurrentWeekIdx().orElseThrow();
        int tryCount = sentenceTestResultRepository.findByMemberNumberAndWeek(member.getMemberNum(), week.getWeekIdx()).size();

        SentenceTestResult testResult = SentenceTestResult.builder()
                .memberNumber(member.getMemberNum())
                .week(week.getWeekIdx())
                .question(problem)
                .pronunciation(convert)
                .build();
        String index = sentenceTestResultRepository.save(testResult).getId();

        WordTestStartResDto resDto = WordTestStartResDto.builder()
                .try_count(tryCount)
                .index(index)
                .problem(problem)
                .convert(convert)
                .build();

        return resDto;
    }

    @Override
    public void saveSentenceTestScore(String index, int score) {
        //들어오는 값은 index,

        Query query = new Query(Criteria.where("_id").is(index));

        Update update = new Update();
        update.set("score", score);

        /*
            이연지는 보아라
            list에 하나를 추가하고싶으면
            update.push("field 명", "추가할 값");
         */

        mongoTemplate.updateFirst(query, update, SentenceTestResult.class);
    }

    @Override
    public String sttWordTest(String index, MultipartFile sound) {

        //받아온 soundfile을 stt로 변환

        //변환된 text를 반환

        return "null";
    }

    @Override
    public String sttSentenceTest(SentenceTestSpeakReqDto reqDto) throws IOException {

        String url = s3Uploader.uploadFiles(reqDto.getSound(), "sentence_test");

        String stt = "im stt";

        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        Update update = new Update().set("user_pronunciation." + reqDto.getOrder(), url);
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

        List<BlankTest> blankList = (List<BlankTest>) blankRes.get("data");


        Map<String, Object> speed1 = client.get()
                .uri("/test/speed1/random")
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
        Week week = weekService.getCurrentWeekIdx().orElseThrow();
        int tryCount = mixTestResultRepository.findByMemberNumberAndWeek(member.getMemberNum(), week.getWeekIdx()).size();


        MixTestResult mixTestResult = MixTestResult.builder()
                .memberNumber(member.getMemberNum())
                .week(week.getWeekIdx())
                .tryCount(tryCount)
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
    public String sttMixTest(MixTestSpeekReqDto reqDto, String type) throws IOException {
        //s3에 sound파일을 업로드 한다.
        String url = s3Uploader.uploadFiles(reqDto.getSound(), "week_test");

        //받아온 soundfile을 stt로 변환
        String stt = "im stt";

        //url을 mongodb에 저장
        Query query = new Query(Criteria.where("_id").is(reqDto.getIndex()));
        if (type.equals("blank")) {
            Update update = new Update().set("blank_test." + reqDto.getOrder() + ".user_answer", stt);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
            update = new Update().set("blank_test." + reqDto.getOrder() + ".sound_url", url);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
        } else {
            Update update = new Update().set("speed_test1." + reqDto.getOrder() + ".user_answer", stt);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
            update = new Update().set("speed_test1." + reqDto.getOrder() + ".sound_url", url);
            mongoTemplate.updateFirst(query, update, MixTestResult.class);
        }


        return stt;
    }

    @Override
    public void saveWeekTestSpeed2(String index, String url, int order) {
        Query query = new Query(Criteria.where("_id").is(index));
        Update update = new Update().set("speed_test2." + order + ".user_answer", url);
        mongoTemplate.updateFirst(query, update, MixTestResult.class);
    }

    @Override
    public void saveWeekTestScore(String index, int score) {
        Query query = new Query(Criteria.where("_id").is(index));
        Update update = new Update();
        update.set("score", score);
        mongoTemplate.updateFirst(query, update, MixTestResult.class);
    }

}
