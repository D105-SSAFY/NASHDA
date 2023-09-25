package com.ssafy.nashda.game.service;

import com.ssafy.nashda.game.dto.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.ImgWordSetResponseDto;

import java.util.List;

public interface GameService {
//    ImgWordSetResponseDto saveImgWordSet(ImgWordSetSaveRequestDto requestDto) throws Exception;

    ImgWordSetResponseDto getImgWordSet(long index) throws Exception;

    ImgWordSetListResponseDto getImgWordSetList(long index) throws  Exception;
    List<BlankSetResponseDto> getBlankSetList() throws Exception;

    long getSpeedSetNum();
}
