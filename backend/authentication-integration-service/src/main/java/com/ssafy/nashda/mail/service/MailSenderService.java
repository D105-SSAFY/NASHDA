package com.ssafy.nashda.mail.service;

import javax.mail.MessagingException;

public interface MailSenderService {
    String makeRandomCode();
    void sendCode(String email) throws MessagingException;
}
