package com.ssafy.nashda.member.service;

public interface MailSenderService {
    String makeRandomCode();
    void mailSend(String email, String title, String message);
}
