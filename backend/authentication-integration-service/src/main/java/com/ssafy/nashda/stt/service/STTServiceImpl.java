package com.ssafy.nashda.stt.service;


import com.ssafy.nashda.common.dto.InternalResponseDto;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.error.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class STTServiceImpl implements STTService {

    @Value("${env.STT_URL}")
    private String URL;

    @Override
    public String getPronunciation(MultipartFile sound) throws Exception {
        // wav 파일만 체크
        String fileName = sound.getName();
        String extension = StringUtils.getFilenameExtension(sound.getOriginalFilename());
        log.info("EXTENSION : {}", extension);

        if(!extension.equals("wav")){
            throw new BadRequestException(ErrorCode.NOT_VALID_EXTENSION);
        }

        // 문제 서버에 요청
        WebClient client = WebClient.builder()
                .baseUrl(URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file_upload", sound.getResource());

        ResponseEntity<InternalResponseDto> response = client.post()
                .uri("/pron")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .onStatus(
                        HttpStatus.BAD_REQUEST::equals,
                        clientResponse -> clientResponse.bodyToMono(ErrorResponse.class).map(s -> {
                            log.info("s : {}", s.getErrorCode());
                            // 에러 코드 별로 예외 처리 가능
                            if (s.getErrorCode() == 4000) {
                                return new BadRequestException(ErrorCode.NOT_EXISTS_DATA);
                            }

                            return new BadRequestException(ErrorCode.TEST);
                        })
                )
                .toEntity(InternalResponseDto.class)
                .block();

//        log.info("response : {}, type : {}",response.getBody().getStatus(), response.getBody().getStatus().getClass().getName());
        String status = response.getBody().getStatus();
        if("400".equals(status)){
            throw new BadRequestException(ErrorCode.STT_ERROR);
        }

        String stt = (String) response.getBody().getData();
        return stt;
    }
}
