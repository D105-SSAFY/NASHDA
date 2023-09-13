package com.ssafy.nashda.notice;


import com.ssafy.nashda.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("/notice")
    public Long create(RequestBody )
}
