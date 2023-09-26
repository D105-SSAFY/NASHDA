package com.ssafy.nashda.game.service;

import com.ssafy.nashda.game.dto.request.BlankResultReqDto;
import com.ssafy.nashda.game.dto.request.GameSTTReqDto;
import com.ssafy.nashda.game.dto.request.SpeedResultReqDto;
import com.ssafy.nashda.game.dto.response.BlankSetResponseDto;
import com.ssafy.nashda.game.dto.response.GmaeSTTResDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetListResponseDto;
import com.ssafy.nashda.game.dto.response.ImgWordSetResponseDto;
import com.ssafy.nashda.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;

public interface GameService {
    //    ImgWordSetResponseDto saveImgWordSet(ImgWordSetSaveRequestDto requestDto) throws Exception;
    ImgWordSetResponseDto getImgWordSet(long index) throws Exception;

    ImgWordSetListResponseDto getImgWordSetList(long index) throws Exception;

    List<BlankSetResponseDto> getBlankSetList() throws Exception;
    long getSpeedSetNum();

    GmaeSTTResDto convertSTT(GameSTTReqDto request) throws Exception;

    void saveSpeedResult(SpeedResultReqDto request, Member member) throws Exception;
    void saveBlankResult(BlankResultReqDto request, Member member) throws Exception;

}
