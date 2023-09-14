package com.ssafy.nashda.notice;


import com.ssafy.nashda.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public Long create(RequestBody NoticeCreateReqDto reqDto) {
        return noticeService.create(reqDto);
    }
}
