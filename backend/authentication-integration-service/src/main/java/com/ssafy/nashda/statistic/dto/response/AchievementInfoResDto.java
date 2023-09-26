package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.Achievement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AchievementInfoResDto {
    String name;
    LocalDate create_on;
    String type;
    String description;

    @Builder
    public AchievementInfoResDto(Achievement achievement) {
        this.name = achievement.getName();
        this.type = achievement.getType();
        this.description = achievement.getDescription();
    }
}
