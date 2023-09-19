package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import org.springframework.web.multipart.MultipartFile;

/**
 *  발음 연습 서비스
 *  2023-09-19
 *  조경호
 *  */

public interface PracticePronService {
    // 단어 연습 문제 불러오기 및 저장 기능
    PronWordSet getPronWordSets(int index) throws Exception;
    PronWordSet savePronWordSet() throws Exception;

    // 구 연습 문제 불러오기 및 저장 기능
    PronPhaseSet getPronPhaseSets(int index) throws Exception;
    PronPhaseSet savePronPhaseSet() throws Exception;


    // 절 연습 문제 불러오기 및 저장 기능
    PronSimpleSet getPronSimpleSets(int index) throws Exception;
    PronSimpleSet savePronSimpleSet() throws Exception;


    // 복합절 연습 문제 불러오기 및 저장 기능
    PronComplexSet getPronComplexSets(int index) throws Exception;
    PronComplexSet savePronComplexSet() throws Exception;

    // 문제 개수 반환
    // 어떤 문제 불러올지를 인자로 받아옴
    long getPronSetNum(String seqName) throws Exception;


    String getSTT(MultipartFile multipartFile, long index, String type);
}
