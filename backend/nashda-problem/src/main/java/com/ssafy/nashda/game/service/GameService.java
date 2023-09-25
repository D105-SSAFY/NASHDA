package com.ssafy.nashda.game.service;

import com.ssafy.nashda.game.dto.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.BlankSetSaveRequestDto;
import com.ssafy.nashda.game.dto.ImgWordSetSaveRequestDto;
import com.ssafy.nashda.game.entity.BlankQuestionSet;
import com.ssafy.nashda.game.entity.ImgWordSet;

import java.util.List;


public interface GameService {
    ImgWordSet saveImgWordSet(ImgWordSetSaveRequestDto requestDto) throws Exception;

    ImgWordSet getImgWordSet(long index) throws Exception;

    List<ImgWordSet> getImgWordSetList(long index) throws  Exception;

    BlankQuestionSet saveBlankSet(BlankSetSaveRequestDto requestDto) throws Exception;

    List<BlankQuestionSet> getBlankSetList() throws Exception;
    long getGameSetNum(String seqName);
}
