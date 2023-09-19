import React from "react";
import * as r from "./style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
import greet from "assets/image/partying-face.png";

export default function result() {
    return (
        <>
            <r.Box>
                <r.AnswerList>
                    <r.AnswerListItem>
                        <r.HeaderLarge correct={true}>{'"'}</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>안녕하세요.</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>처음</r.HeaderLarge>
                        <r.HeaderLarge correct={false}>마라</r.HeaderLarge>
                        <r.HeaderLarge correct={false}>반가슨니다.</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>{'"'}</r.HeaderLarge>
                    </r.AnswerListItem>
                    <r.AnswerListItem>
                        <r.HeaderLarge correct={true}>{'"'}</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>쌍쌍바는</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>정말</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>시원해요.</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>친구도</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>좋아해요.</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>{'"'}</r.HeaderLarge>
                    </r.AnswerListItem>
                    <r.AnswerListItem>
                        <r.HeaderLarge correct={true}>{'"'}</r.HeaderLarge>
                        <r.HeaderLarge correct={false}>아면을</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>좋아하세요?</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>치킨을</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>좋아하세요?</r.HeaderLarge>
                        <r.HeaderLarge correct={true}>{'"'}</r.HeaderLarge>
                    </r.AnswerListItem>
                </r.AnswerList>

                <r.ThankTextBox>
                    <r.HeaderSmall>아름다운 목소리르 들려주셔서 고마워요.</r.HeaderSmall>
                    <r.HeaderSmall>우리 함께 내쉬다로 더 연습해 볼까요?</r.HeaderSmall>
                    <r.RegistLink to="/signup">
                        시작하기
                        <ArrowCircleRightOutlinedIcon />
                    </r.RegistLink>
                    <r.Greet src={greet}></r.Greet>
                </r.ThankTextBox>
            </r.Box>
        </>
    );
}
