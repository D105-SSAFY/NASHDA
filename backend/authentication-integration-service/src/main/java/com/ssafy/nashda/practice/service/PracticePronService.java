package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.member.entity.Member;
import com.ssafy.nashda.practice.dto.PracticePronRequestDto;
import com.ssafy.nashda.practice.dto.PronResponseDto;
import com.ssafy.nashda.practice.dto.PronSTTResponseDto;
import org.springframework.web.multipart.MultipartFile;

/**
 *  발음 연습 서비스
 *  2023-09-19
 *  조경호
 *  */

public interface PracticePronService {
    // 단어 연습 문제 불러오기 및 저장 기능
    PronResponseDto getPronWordSets(int index) throws Exception;

    // 구 연습 문제 불러오기 및 저장 기능
    PronResponseDto getPronPhaseSets(int index) throws Exception;


    // 절 연습 문제 불러오기 및 저장 기능
    PronResponseDto getPronSimpleSets(int index) throws Exception;


    // 복합절 연습 문제 불러오기 및 저장 기능
    PronResponseDto getPronComplexSets(int index) throws Exception;
    // 문제 개수 반환
    // 어떤 문제 불러올지를 인자로 받아옴
    long getPronSetNum(String seqName) throws Exception;

    PronSTTResponseDto getPracSTT(Member member, MultipartFile sound, PracticePronRequestDto practicePronRequestDto) throws Exception;
}
