package com.ssafy.nashda.common.text.service;

import com.ssafy.nashda.common.error.code.ErrorCode;
import com.ssafy.nashda.common.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextProcessService {
    public final static String[] ONSET = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"}; // 초성

    public final static String[] NUCLEUS = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"}; // 중성

    public final static String[] CODA = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
            "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"}; // 종성

    public String getOnset(char uniVal) throws Exception {
        // 한글일 경우만 시작해야 하기 때문에 0xAC00부터 아래의 로직을 실행한다
        if (uniVal >= 0xAC00) {
            uniVal = (char) (uniVal - 0xAC00);

            char onset = (char) (uniVal / 28 / 21);
            return ONSET[onset];
        }

        // 한글이 아닌 경우
        throw new BadRequestException(ErrorCode.NOT_KOREAN);
    }

    public String getNucleus(char uniVal) throws Exception {
        // 한글일 경우만 시작해야 하기 때문에 0xAC00부터 아래의 로직을 실행한다
        if (uniVal >= 0xAC00) {
            uniVal = (char) (uniVal - 0xAC00);

            char nucleus = (char) ((uniVal) / 28 % 21);
            return NUCLEUS[nucleus];
        }

        // 한글이 아닌 경우
        throw new BadRequestException(ErrorCode.NOT_KOREAN);
    }

    public String getCoda(char uniVal) throws Exception {
        // 한글일 경우만 시작해야 하기 때문에 0xAC00부터 아래의 로직을 실행한다
        if (uniVal >= 0xAC00) {
            uniVal = (char) (uniVal - 0xAC00);

            char coda = (char) (uniVal % 28);
            return CODA[coda];
        }

        // 한글이 아닌 경우
        throw new BadRequestException(ErrorCode.NOT_KOREAN);
    }


    // 틀린 문자열 반환
    public int[] findIncorrectString(String originConvert, String stt) { // str1, str2
        String originConvertTrim = originConvert.trim().replaceAll("[\\s!@#$%^&*().]", "");
        String sttTrim = stt.trim().replaceAll("[\\s!@#$%^&*().]", "");

        int m = originConvertTrim.length();
        int n = sttTrim.length();
        int maxLen = 0;
        int endIndex = 0;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (originConvertTrim.charAt(i - 1) == sttTrim.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i - 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        if (maxLen == 0) { // 맞는게 하나도 없음
            return new int[]{-1, -1};

        } else { // 일치하는 문자열이 존재한다.

            int startIndex = endIndex - maxLen + 1; // 공통되는 문자열의 시작 인덱스

            return new int[]{startIndex, endIndex}; // 공통되는 문자열 인덱스 범위 반환
        }
    }
}