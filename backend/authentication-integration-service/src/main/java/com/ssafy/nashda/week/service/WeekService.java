package com.ssafy.nashda.week.service;

import com.ssafy.nashda.week.entity.Week;
import com.ssafy.nashda.week.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeekService {

    private final WeekRepository weekRepository;
    @Scheduled(cron = "0 0 0 * * 1")
    @Transactional
    public void run() {
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = startDateTime.plusDays(6).with(LocalTime.MAX);

        Week week = Week.builder()
                .startDate(startDateTime)
                .endDate(endDateTime)
                .build();

        weekRepository.save(week);
    }

    @Transactional(readOnly = true)
    public Optional<Week> getCurrentWeekIdx() {
        return weekRepository.findCurrentWeekIdx();
    }

}
