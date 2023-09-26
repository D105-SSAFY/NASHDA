    package com.ssafy.nashda.game.dto.request;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.springframework.web.multipart.MultipartFile;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class GameSTTReqDto {
        private int type;   //문제 유형 0:speed1, 1:speed2, 2:blank
        private int index;  //문제 번호
        private String answer;  //정답
        private MultipartFile sound;
    }
