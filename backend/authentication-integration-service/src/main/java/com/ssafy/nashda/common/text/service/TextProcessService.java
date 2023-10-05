package com.ssafy.nashda.common.text.service;

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


    public final static String[] CONSONANT = {
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B1.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B2.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B4.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B7.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B8.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B9.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%81.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%82.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%83.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%85.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%86.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%87.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%88.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%89.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8A.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8B.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8C.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8D.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8E.png"
    };

    //    "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
//            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
    // 모음 발음 사진 모음
    public final static String[] VOWEL = {
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%8F.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%90.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%91.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%92.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%93.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%94.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%95.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%96.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%97.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%98.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%99.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%9A.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%9B.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%9C.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%9D.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%9E.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%9F.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%A0.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%A1.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%A2.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/vowel/%E3%85%A3.png"
    };

    public final static String[] CONSONANT_CODA = {
            "",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B1.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B2.png",
            "",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B4.png",
            "",
            "",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B7.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%84%B9.png",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%81.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%82.png", // ㅂ
            "",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%85.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%86.png", // ㅆ
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%87.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%88.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8A.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8B.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8C.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8D.png",
            "https://nashda.s3.ap-northeast-2.amazonaws.com/mouth/consonant/%E3%85%8E.png"
    };

    public int getOnsetIndex(char uniVal) throws Exception {

        uniVal = (char) (uniVal - 0xAC00);

        char onset = (char) (uniVal / 28 / 21);
        return onset;

    }

    public String getOnset(char uniVal) throws Exception {

        uniVal = (char) (uniVal - 0xAC00);

        char onset = (char) (uniVal / 28 / 21);
        return ONSET[onset];

    }

    public String getNucleus(char uniVal) throws Exception {

        uniVal = (char) (uniVal - 0xAC00);

        char nucleus = (char) ((uniVal) / 28 % 21);
        return NUCLEUS[nucleus];

    }

    public int getNucleusIndex(char uniVal) throws Exception {

        uniVal = (char) (uniVal - 0xAC00);

        char nucleus = (char) ((uniVal) / 28 % 21);
        return nucleus;

    }

    public String getCoda(char uniVal) throws Exception {

        uniVal = (char) (uniVal - 0xAC00);

        char coda = (char) (uniVal % 28);
        return CODA[coda];

    }

    public int getCodaIndex(char uniVal) throws Exception {

        uniVal = (char) (uniVal - 0xAC00);

        char coda = (char) (uniVal % 28);
        return coda;

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

    public String findLCS(String originConvert, String pronunciation) {
        int m = originConvert.length();
        int n = pronunciation.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (originConvert.charAt(i - 1) == pronunciation.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcsLength = dp[m][n];
        char[] lcs = new char[lcsLength];
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (originConvert.charAt(i - 1) == pronunciation.charAt(j - 1)) {
                lcs[--lcsLength] = originConvert.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }

}