package com.ssafy.nashda.practice.service;

import com.ssafy.nashda.practice.dto.PronSaveRequestDto;
import com.ssafy.nashda.practice.entity.PronComplexSet;
import com.ssafy.nashda.practice.entity.PronPhaseSet;
import com.ssafy.nashda.practice.entity.PronSimpleSet;
import com.ssafy.nashda.practice.entity.PronWordSet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *  발음 연습 서비스
 *  2023-09-19
 *  조경호
 *  */

public interface PracticePronService {
    // 단어 연습 문제 불러오기 및 저장 기능
    PronWordSet getPronWordSets(int index) throws Exception;
    PronWordSet savePronWordSet(PronSaveRequestDto pronSaveRequestDto) throws Exception;
    List<PronWordSet> findTestWordSet() throws Exception;

    // 구 연습 문제 불러오기 및 저장 기능
    PronPhaseSet getPronPhaseSets(int index) throws Exception;
    PronPhaseSet savePronPhaseSet(PronSaveRequestDto pronSaveRequestDto) throws Exception;


    // 절 연습 문제 불러오기 및 저장 기능
    PronSimpleSet getPronSimpleSets(int index) throws Exception;
    PronSimpleSet savePronSimpleSet(PronSaveRequestDto pronSaveRequestDto) throws Exception;
    List<PronSimpleSet> findTestSimpleSet() throws Exception;



    // 복합절 연습 문제 불러오기 및 저장 기능
    PronComplexSet getPronComplexSets(int index) throws Exception;
    PronComplexSet savePronComplexSet(PronSaveRequestDto pronSaveRequestDto) throws Exception;
//    List<PronComplexSet> findTestComplexSet() throws Exception;

    // 문제 개수 반환
    // 어떤 문제 불러올지를 인자로 받아옴
    long getPronSetNum(String seqName) throws Exception;


}
