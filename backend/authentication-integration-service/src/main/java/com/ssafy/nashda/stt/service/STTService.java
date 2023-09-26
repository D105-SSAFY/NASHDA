package com.ssafy.nashda.stt.service;

import org.springframework.web.multipart.MultipartFile;

public interface STTService {

    public String getPronunciation(MultipartFile sound) throws Exception;
}
