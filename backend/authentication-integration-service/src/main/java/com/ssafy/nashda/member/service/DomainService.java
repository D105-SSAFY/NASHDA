package com.ssafy.nashda.member.service;

import com.ssafy.nashda.member.entity.Hobby;
import com.ssafy.nashda.member.entity.Job;

import java.util.List;
import java.util.Optional;

public interface DomainService {
    List<Hobby> getHobbies();
    Optional<Hobby> findByHobbyIdx(Long hobbyIdx);
    List<Job> getJobs();
    Optional<Job> findByJobIdx(Long jobIdx);
}
