package com.ssafy.nashda.member.service;

import com.ssafy.nashda.member.entity.Hobby;
import com.ssafy.nashda.member.entity.Job;
import com.ssafy.nashda.member.repository.HobbyRepository;
import com.ssafy.nashda.member.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("DomainService")
@Transactional
@RequiredArgsConstructor
public class DomainServiceImple implements DomainService{

    private final HobbyRepository hobbyRepository;
    private final JobRepository jobRepository;

    @Override
    public List<Hobby> getHobbies() {
        return hobbyRepository.findAll();
    }
    @Override
    public Optional<Hobby> findByHobbyIdx(Long hobbyIdx) {
        return hobbyRepository.findByHobbyIdx(hobbyIdx);
    }
    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }
    @Override
    public Optional<Job> findByJobIdx(Long jobIdx) {
        return jobRepository.findByJobIdx(jobIdx);
    }
}
