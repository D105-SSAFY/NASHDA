package com.ssafy.nashda.game.service;

import com.ssafy.nashda.game.dto.request.*;
import com.ssafy.nashda.game.dto.response.*;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GameService {
    ImgWordSetResponseDto getImgWordSet(long index) throws Exception;

    ImgWordSetListResponseDto getImgWordSetList(long index) throws Exception;

    List<BlankSetResponseDto> getBlankSetList() throws Exception;

    long getSpeedSetNum();

    GameSTTResDto convertSTT(MultipartFile sound, GameSTTReqDto request) throws Exception;

    void saveSpeedResult(SpeedResultReqDto request, Member member) throws Exception;

    int saveBlankResult(BlankResultReqDto request, Member member) throws Exception;

    ImgWordSetResponseDto saveImgWordSet(ImgWordSetSaveReqDto imgWordSetSaveReqDto) throws Exception;

    BlankSetResponseDto saveBlankSet(BlankSetSaveReqDto blankSetSaveReqDto) throws Exception;



}
