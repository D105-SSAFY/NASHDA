package com.ssafy.nashda.mail;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.mail.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class MailController {

    private final MailSenderService mailSenderService;
    @PostMapping("/sendcode")
    public ResponseEntity<? extends BaseResponseBody> refreshToken(@RequestBody Map<String, Object> map) throws MessagingException {
        System.out.println("뮁");
        if(map.get("email")==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "이메일 입력 필수"));

        String email = map.get("email").toString();
        log.info("email : {}", email);
        mailSenderService.sendCode(email);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "이메일 전송 성공"));
    }
}
