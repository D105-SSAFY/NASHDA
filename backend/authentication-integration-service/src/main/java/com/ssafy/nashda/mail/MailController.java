package com.ssafy.nashda.mail;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.mail.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class MailController {

    private final MailSenderService mailSenderService;

    @PostMapping("/sendcode")
    public ResponseEntity<? extends BaseResponseBody> sendCodeToMail(@RequestBody Map<String, Object> map) throws MessagingException {
        System.out.println("뮁");
        if (map.get("email") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "이메일 입력 필수"));

        String email = map.get("email").toString();
        log.info("email : {}", email);
        mailSenderService.sendCode(email);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "이메일 전송 성공"));
    }

    @PostMapping("/checkcode")
    public ResponseEntity<? extends BaseResponseBody> checkCodeFromRedis
            (@RequestBody Map<String, Object> map) throws MessagingException {

        if (map.get("email") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "이메일 입력 필수"));
        if (map.get("code") == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "인증코드 입력 필수"));

        String email = map.get("email").toString();
        String code = map.get("code").toString();
        log.info("email : {}", email);
        log.info("code : {}", code);
        boolean result = mailSenderService.checkCode(email, code);
        if (result)
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody<>(200, "인증코드 일치"));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponseBody<>(400, "인증코드 불일치"));
    }

}
