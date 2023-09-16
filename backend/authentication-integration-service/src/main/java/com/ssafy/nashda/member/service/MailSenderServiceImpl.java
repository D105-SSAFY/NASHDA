package com.ssafy.nashda.member.service;

import com.ssafy.nashda.member.service.MailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/*
    유저의 인증 관련 기능 : 로그인, 이메일 인증 -> authorization에 저장
    아래의 서비스는 유저가 입력한 email로 인증 코드를 보내기 위함이다.
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

    private JavaMailSenderImpl javaMailSender;
    //10자리의 랜덤 문자열을 생성하기 위한 기본 setting
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int CODE_LENGTH = 10;
    private static final SecureRandom RANDOM = new SecureRandom();
    //----------------------------------------------



    @Override
    public String makeRandomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    @Override
    public void mailSend(String email, String title, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();

    }
}
