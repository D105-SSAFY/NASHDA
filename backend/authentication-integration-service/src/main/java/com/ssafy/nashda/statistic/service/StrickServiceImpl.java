package com.ssafy.nashda.statistic.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.statistic.entity.Strick;
import com.ssafy.nashda.statistic.repository.StrickRepository;
import com.ssafy.nashda.week.entity.Week;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StrickServiceImpl implements StrickService {

    private final StrickRepository strickRepository;

    @Override
    public List<Strick> getStrick(Member member) {
        //member의 stick을 최대 365개 보내준다.
        Pageable pageable = PageRequest.of(0, 365);
        return strickRepository.findByMember(member, pageable);
    }

    @Override
    public void initStrick(Member member) {
        Strick strick = Strick.builder()
                .member(member)
                .build();
        strickRepository.save(strick);
    }

    @Override
    public boolean isExistStrick(Member member) {
        return strickRepository.existsByMemberAndCreatOn(member, LocalDate.now());
    }

    @Override
    public void increaseBlankCount(Member member) {
        Strick strick = strickRepository.findByMemberAndCreatOn(member, LocalDate.now()).orElseThrow(() -> new IllegalArgumentException("해당하는 strick이 없습니다."));
        strickRepository.updateBlankCount(strick.getBlankCount() + 1, strick.getIndex());
    }

    @Override
    public void increasePracticeCount(Member member) {
        Strick strick = strickRepository.findByMemberAndCreatOn(member, LocalDate.now()).orElseThrow(() -> new IllegalArgumentException("해당하는 strick이 없습니다."));
        strickRepository.updatePracticeCount(strick.getPracticeCount() + 1, strick.getIndex());
    }

    @Override
    public void increaseSpeedCount(Member member) {
        Strick strick = strickRepository.findByMemberAndCreatOn(member, LocalDate.now()).orElseThrow(() -> new IllegalArgumentException("해당하는 strick이 없습니다."));
        strickRepository.updateSpeedCount(strick.getSpeedCount() + 1, strick.getIndex());
    }

    @Override
    public void increaseTestCount(Member member) {
        Strick strick = strickRepository.findByMemberAndCreatOn(member, LocalDate.now()).orElseThrow(() -> new IllegalArgumentException("해당하는 strick이 없습니다."));
        strickRepository.updateTestCount(strick.getTestCount() + 1, strick.getIndex());
    }

    @Override
    public boolean isExistStrickBefore(Member member) {
        //어제 날짜의 strick이 존재하는지 확인
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return strickRepository.existsByMemberAndCreatOn(member, yesterday);
    }


}
