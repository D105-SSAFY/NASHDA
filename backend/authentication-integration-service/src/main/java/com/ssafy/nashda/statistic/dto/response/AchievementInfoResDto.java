package com.ssafy.nashda.statistic.dto.response;

import com.ssafy.nashda.statistic.entity.Achievement;
import com.ssafy.nashda.statistic.entity.MemberAchievement;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class AchievementInfoResDto {
    String name;
    LocalDate create_on;
    String type;
    String description;
    Long achievement_index;

    private String convertTypeToKorean(String type) {
        switch (type) {
            case "word":
                return "단어";
            case "test_word":
                return "단어 테스트";
            case "test_sentence":
                return "문장 테스트";
            case "test_week":
                return "주간 테스트";
            case "game_blank":
                return "빈칸 게임";
            case "game_speed":
                return "스피드 게임";
            case "game":
                return "게임";
            case "practice":
                return "연습";
            case "test":
                return "테스트";
            case "continuous_login":
                return "연속 로그인";
            case "conversation":
                return "대화";
            case "practice_word":
                return "단어 연습";
            case "practice_sentence":
                return "문장 연습";
            case "sentence":
                return "문장";
            default:
                return "없음";

        }
    }

    @Builder
    public AchievementInfoResDto(Achievement achievement, LocalDateTime achievedTime) {
        this.name = achievement.getName();
        this.type = convertTypeToKorean(achievement.getType());
        this.description = achievement.getDescription();
        this.create_on = achievedTime.toLocalDate();
        this.achievement_index = achievement.getIndex();

    }

    public static AchievementInfoResDto fromMemberAchievement(MemberAchievement memberAchievement) {
        return AchievementInfoResDto.builder()
                .achievement(memberAchievement.getAchievement())
                .achievedTime(memberAchievement.getAchievedDate())
                .build();
    }
}
