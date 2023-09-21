package com.ssafy.nashda.simulGPT;


import com.ssafy.nashda.simulGPT.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/simul")
public class ChatGptController {
    private final ChatService chatService;

    @PostMapping
    public String test(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }
}
