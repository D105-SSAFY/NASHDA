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

        @Builder
        public AchievementInfoResDto(Achievement achievement, LocalDateTime achievedTime) {
            this.name = achievement.getName();
            this.type = achievement.getType();
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
