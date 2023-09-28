package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.repository.StrickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StrickServiceImpl implements StrickService{

    private final StrickRepository strickRepository;
    @Override
    public List<Strick> getStrick(Member member) {
        //member의 stick을 최대 365개 보내준다.
        Pageable pageable = PageRequest.of(0, 365);
        return strickRepository.findByMember(member, pageable);
    }


}
