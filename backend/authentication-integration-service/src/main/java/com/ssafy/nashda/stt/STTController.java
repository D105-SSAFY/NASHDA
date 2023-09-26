package com.ssafy.nashda.stt;

import com.ssafy.nashda.stt.service.STTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class STTController {
    private final STTService sttService;

    @PostMapping(value = "/stt", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public String getSTT(@RequestPart(value = "sound", required = true) MultipartFile sound) throws Exception {
        return sttService.getPronunciation(sound);
    }

}
