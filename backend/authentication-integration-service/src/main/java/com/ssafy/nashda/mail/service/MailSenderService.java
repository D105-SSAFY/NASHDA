package com.ssafy.nashda.mail.service;

import javax.mail.MessagingException;

public interface MailSenderService {
    String makeRandomCode();
    void sendCode(String email) throws MessagingException;
    //코드일치 체크
    boolean checkCode(String email, String code);
}
