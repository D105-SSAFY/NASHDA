package com.ssafy.nashda.member.controller;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Reponse.DomainResDto;
import com.ssafy.nashda.member.entity.Hobby;
import com.ssafy.nashda.member.entity.Job;
import com.ssafy.nashda.member.repository.HobbyRepository;
import com.ssafy.nashda.member.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class DomainController {

    private final JobRepository jobRepository;
    private final HobbyRepository hobbyRepository;

    //hobby와 domain을 묶어서 보내준다.
    @GetMapping("/domain")
    public ResponseEntity<? extends BaseResponseBody> domainList() {

        log.info("도메인 조회");
        List<Job> jobList = jobRepository.findAll();
        List<Hobby> hobbyList = hobbyRepository.findAll();

        if (jobList.isEmpty() || hobbyList.isEmpty()) {
            return ResponseEntity.status(404).body(new BaseResponseBody<>(404, "도메인 조회 실패"));
        }

        DomainResDto domainResDto = new DomainResDto(jobList, hobbyList);

        return ResponseEntity.status(200).body(new BaseResponseBody<>(200, "도메인 조회 성공", domainResDto));
    }


}
