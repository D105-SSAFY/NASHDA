package com.ssafy.nashda.stt.service;

import org.springframework.web.multipart.MultipartFile;

public interface STTService {

    public String getPronunciation(MultipartFile sound) throws Exception; // 발음 그대로
    public String getText(MultipartFile sound) throws Exception;
}
