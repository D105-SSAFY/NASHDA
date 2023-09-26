package com.ssafy.nashda.simulGPT;


import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
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

    @PostMapping("/{background}")
    public ResponseEntity<? extends BaseResponseBody> saveMessage(@Valid
                                                                  @PathVariable String background,
                                                                  @RequestBody MessageReqDto messageReqDto) {

        List<ChatMessageDto> messages = new ArrayList<>();
        MemorizeChat memorizeChat = new MemorizeChat();

        // 해당 유저가 처음 보낸 요청인지 재요청인지 Id 확인
        if (ObjectUtils.isEmpty(messageReqDto.getId())) {
            if (background.equals("cafe")) {
                messages.add(new ChatMessageDto("system", "장소 : 카페 상황 : 손님이 카페에 음료를 사러 왔음. " +
                        "조건1: 카페에서 음료를 구매하고 손님이 음료를 받으면 상황 종료, 종료 되었을 경우 : '상황 종료' && 바리스타의 대답의 형태로 출력 " +
                        "조건2: 상황과 장소에 맞지 않을 경우 '옳지않음',옳지않은 이유,바리스타의 대답 출력" +
                        "조건3: 카페에서는 음료와 디저트만 주문할 수 있음, 주문 범위를 벗어나면 상황에 맞지 않다고 판단 " +
                        "조건4: 너는 바리스타야."));
                messages.add(new ChatMessageDto("assistant", "어서오세요. nashda카페입니다. 주문 도와드릴까요?"));

            } else if (background.equals("police")) {
                messages.add(new ChatMessageDto("system", "장소 : 경찰서 상황 : 내가 분실물을 찾으러 경찰서에 왔음. " +
                        "조건1: 내가 분실물을 수령하면 상황 종료, 종료되었을 경우 : '상황 종료' && 경찰관의 대답의 형태로 출력 " +
                        "조건2: 상황과 장소에 맞지 않을 경우 '옳지않음', 옳지 않은 이유, 경찰관의 대답 출력" +
                        "조건3: 분실물을 찾으러 온 것 외의 길찾기, 범인신고 등의 업무는 상황에 맞지 않다고 판단함." +
                        "조건4: 너는 경찰관이야."));
                messages.add(new ChatMessageDto("assistant", "경찰서입니다. 무엇을 도와드릴까요?"));

            } else {
                messages.add(new ChatMessageDto("system", "장소 : 영화관 상황 : 나는 영화 예매와 팝콘 및 음료와 같은 먹거리를 구매하러 영화관에 왔어." +
                        "조건1: 영화 예매 및 먹거리를 구매 대화까지 나누면 상황 종료, 종료되었을 경우 : '상황 종료' && 영화관 직원의 대답의 형태로 출력" +
                        "조건2: 상황과 장소에 맞지 않을 경우 '옳지않음',옳지않은 이유,영화관 직원의 대답 출력" +
                        "조건3: 영화 예매와 팝콘 및 음료와 같은 스낵류 구매를 제외한 것은 상황에 맞지 않다고 판단함." +
                        "조건4: 영화 예매를 먼저한 후 팝콘 및 음료와 같은 스낵류를 구매할 수 있음." +
                        "조건5: 너는 영화관 직원이야."));
                messages.add(new ChatMessageDto("assistant", "어서오세요. nashda 극장입니다. 어떤 영화 예매를 도와드릴까요?"));
            }

        } else {
             memorizeChat = chatGptRepository.findById(messageReqDto.getId())
                    .orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_MESSAGE));
            messages = memorizeChat.getMessages();
            chatGptRepository.deleteById(messageReqDto.getId());
        }

        messages.add(new ChatMessageDto("user", messageReqDto.getMessage()));

        ChatResDto chatResDto = chatGptServiceImpl.getChatCompletion(messages);

        String message = chatResDto.getChoices().get(0).getMessage().getContent();
        // 상황에 옳은 답변인지 판단
        if (message.contains("옳지않음.")) {
            String temp[] = message.split("옳지않음.");
            chatResDto.setCorrect(Boolean.FALSE);
            chatResDto.getChoices().get(0).getMessage().setContent(temp[1].strip());
        } else {
            chatResDto.setCorrect(Boolean.TRUE);
        }

        if (message.contains("상황 종료")) {
            String temp[] = message.split("상황 종료");
            chatResDto.setFinish(Boolean.TRUE);
            chatResDto.getChoices().get(0).getMessage().setContent(temp[0].strip());


        } else {
            chatResDto.setFinish(Boolean.FALSE);
        }

        messages.add(new ChatMessageDto(chatResDto.getChoices().get(0).getMessage().getRole(), chatResDto.getChoices().get(0).getMessage().getContent()));
        memorizeChat.setMessages(messages);

        String newId = chatGptRepository.save(memorizeChat).getId();
        chatResDto.setId(newId);

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseBody(200, "gpt 응답 성공", chatResDto));

    }

}
