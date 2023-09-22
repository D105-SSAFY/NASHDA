package com.ssafy.nashda.common.error.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // S3
    FAIL_DELETE_FILE(HttpStatus.INTERNAL_SERVER_ERROR, 7000, "S3에 업로드 된 파일을 지울 때 오류가 발생하였습니다."),


    NOT_KOREAN(HttpStatus.BAD_REQUEST, 6000, "한글이 아님"),
    SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "발음 통계 저장 오류"),
    TEST(HttpStatus.INTERNAL_SERVER_ERROR, 001, "exception test"),
    NOT_EXISTS_DATA(HttpStatus.BAD_REQUEST, 4000, "존재하지 않는 데이터입니다."),
    USER_EXIST(HttpStatus.BAD_REQUEST, 4000, "회원이 존재합니다."),
    USER_NOT_EXIST(HttpStatus.BAD_REQUEST, 4001, "회원이 존재하지 않습니다."),
    USER_NOT_MATCH(HttpStatus.BAD_REQUEST, 4002, "아이디 혹은 비밀번호를 확인하세요."),
    INVALID_USER(HttpStatus.BAD_REQUEST,4003,"올바르지 않은 사용자의 접근입니다."),
    TOKEN_DENIED(HttpStatus.BAD_REQUEST, 4004, "토큰이 만료되었습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, 4005, "올바르지 않은 입력값 입니다."),
    FAIL_SENDEMAIL(HttpStatus.BAD_REQUEST, 400, "이메일 전송에 실패하였습니다."),
    TOKEN_NOT_FOUND(HttpStatus.BAD_REQUEST, 400, "토큰이 존재하지 않습니다."),



    // 인증, 인가
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, 401, "해당 토큰은 유효한 토큰이 아닙니다."),
    NOT_EXISTS_AUTHORIZATION(HttpStatus.UNAUTHORIZED, 401, "Authorization Header가 빈 값입니다."),
    NOT_VALID_AUTHORIZATION(HttpStatus.UNAUTHORIZED, 401, "사용자는 관리자의 권한을 가지고 있지 않습니다."),
    NOT_EQUAL_USER(HttpStatus.UNAUTHORIZED, 401, "질문을 작성한 유저가 아닙니다."),

    // 공지사항
    NOT_EXISTS_NOTICE_ID(HttpStatus.BAD_REQUEST, 400, "존재하지 않는 공지사항글입니다."),
    NOT_EXISTS_TITLE(HttpStatus.BAD_REQUEST, 400, "제목은 필수입력항목입니다."),
    NOT_EXISTS_CONTENT(HttpStatus.BAD_REQUEST, 400, "내용은 필수입력항목입니다."),


    // 질문 게시판
    NOT_EXISTS_QUESTION_ID(HttpStatus.BAD_REQUEST, 400, "존재하지 않는 질문글입니다."),

    // 답변
    NOT_EXISTS_REPLY_ID(HttpStatus.BAD_REQUEST, 400, "존재하지 않는 답변글입니다."),
    EXIST_REPLY(HttpStatus.BAD_REQUEST, 400, "이미 답변이 달린 질문입니다.");

    private HttpStatus httpStatus;
    private int errorCode;
    private String message;

    ErrorCode(HttpStatus httpStatus, int errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }
}
