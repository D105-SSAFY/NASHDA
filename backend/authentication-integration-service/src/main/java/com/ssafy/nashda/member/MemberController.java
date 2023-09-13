package com.ssafy.nashda.member;

import com.ssafy.nashda.common.dto.BaseResponseBody;
import com.ssafy.nashda.member.dto.Request.MemberSignUpReqDto;
import com.ssafy.nashda.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "1. User API", description = "user api")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "DB에 해당 member의 정보가 없으면 회원가입 후 로그인 \n\n" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description =  "CREATED", content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\n" +
                            "    \"status\": 201,\n" +
                            "    \"data\": \"CREATED\"\n" +
                            "}"))),
            @ApiResponse(responseCode = "4000", description =  "USER_EXIST", content = @Content(examples = @ExampleObject(value = "{\"status\": 4000, \"data\": \"회원이 존재합니다.\"}")))
            })
    @PostMapping("/signup")
    public ResponseEntity<? extends BaseResponseBody> signup(@RequestBody MemberSignUpReqDto signUpReqDto, @RequestParam(name = "role", required = false) String role ) throws IOException {
        memberService.signUp(signUpReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(201, "CREATED"));
    }
}
