package com.ssafy.nashda.game.service;

import com.ssafy.nashda.game.dto.request.SpeedSTTReqDto;
import com.ssafy.nashda.game.dto.response.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetResponseDto;
import com.ssafy.nashda.game.dto.response.SpeedSTTResDto;

import javax.mail.Multipart;
import java.util.List;

public interface GameService {
//    ImgWordSetResponseDto saveImgWordSet(ImgWordSetSaveRequestDto requestDto) throws Exception;
    ImgWordSetResponseDto getImgWordSet(long index) throws Exception;
    ImgWordSetListResponseDto getImgWordSetList(long index) throws  Exception;
    List<BlankSetResponseDto> getBlankSetList() throws Exception;
    long getSpeedSetNum();
    boolean checkSpeedAnswer(SpeedSTTReqDto reqDto, Multipart sound);

}
