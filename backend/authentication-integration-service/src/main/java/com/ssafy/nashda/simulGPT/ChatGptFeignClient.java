package com.ssafy.nashda.simulGPT;

import com.ssafy.nashda.simulGPT.config.ChatGptHeaderConfig;
import com.ssafy.nashda.simulGPT.dto.ChatReqDto;
import com.ssafy.nashda.simulGPT.dto.ChatResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ChatGptClient", url = "https://api.openai.com/v1", configuration = {ChatGptHeaderConfig.class})
public interface ChatGptFeignClient {
    @PostMapping("/chat/completions")
    ChatResDto chatCompletion(@RequestBody ChatReqDto chatReqDto);
}
