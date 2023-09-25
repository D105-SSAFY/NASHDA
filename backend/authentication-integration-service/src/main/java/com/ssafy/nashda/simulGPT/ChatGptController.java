package com.ssafy.nashda.simulGPT;


import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import com.ssafy.nashda.common.service.CacheService;
import com.ssafy.nashda.simulGPT.dto.*;
import com.ssafy.nashda.simulGPT.entity.MemorizeChat;
import com.ssafy.nashda.simulGPT.repository.ChatGptRepository;
import com.ssafy.nashda.simulGPT.service.ChatGptService;
import com.ssafy.nashda.simulGPT.service.ChatGptServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/simul")
public class ChatGptController {
    private final ChatGptService chatGptService;
    private final ChatGptServiceImpl chatGptServiceImpl;
    private final ChatGptRepository chatGptRepository;

    @PostMapping
    public ResponseEntity<? extends BaseResponseBody> saveMessage(@Valid
                                         @RequestBody MessageReqDto messageReqDto) {

        List<ChatMessageDto> messages = new ArrayList<>();
        MemorizeChat memorizeChat = new MemorizeChat();

        // 해당 유저가 처음 보낸 요청인지 재요청인지 Id 확인
        if (ObjectUtils.isEmpty(messageReqDto.getId())) {
            messages.add(new ChatMessageDto("system", "장소 : 카페 상황 : 손님이 카페에 음료를 사러 왔음. " +
                                                            "조건1: 상황이 종료 되었을 경우 : '상황 종료' && 바리스타의 대답의 형태로 출력 " +
                                                            "조건2: 상황과 장소에 맞지 않을 경우 '옳지않음',옳지않은 이유,바리스타의 대답 출력, 옳지 않은 이유 뒤에 && 붙여줘. " +
                                                            "조건3: 카페에서는 음료와 디저트만 주문할 수 있음, 주문 범위를 벗어나면 상황에 맞지 않다고 판단 " +
                                                            "조건4: 너는 바리스타야."));
            messages.add(new ChatMessageDto("assistant", "어서오세요. nashda카페입니다. 주문 도와드릴까요?"));
        } else {
             memorizeChat = chatGptRepository.findById(messageReqDto.getId())
                    .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_CONTENT));
            messages = memorizeChat.getMessages();
            chatGptRepository.deleteById(messageReqDto.getId());
        }

        messages.add(new ChatMessageDto("user", messageReqDto.getMessage()));

        ChatResDto chatResDto = chatGptServiceImpl.getChatCompletion(messages);

        messages.add(new ChatMessageDto(chatResDto.getChoices().get(0).getMessage().getRole(), chatResDto.getChoices().get(0).getMessage().getContent()));
        memorizeChat.setMessages(messages);

        String newId = chatGptRepository.save(memorizeChat).getId();
        chatResDto.setId(newId);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "gpt 응답 성공", chatResDto));


    }

    // 대화 종료시 마지막 대화 캐시 Id 삭제
}
