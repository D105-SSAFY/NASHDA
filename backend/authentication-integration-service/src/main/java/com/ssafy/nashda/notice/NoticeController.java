package com.ssafy.nashda.notice;


import com.ssafy.nashda.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

//    @PostMapping
//    public Long addNotice(RequestBody NoticeCreateReqDto reqDto) {
//        return noticeService.create(reqDto);
//    }
}
