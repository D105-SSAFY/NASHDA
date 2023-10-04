import React from "react";

import * as r from "./style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
import greet from "assets/image/partying-face.png";

export default function result({ props: { result } }) {
    return (
        <>
            <r.Box>
                <r.AnswerList>
                    {result.map((sentence) => {
                        return (
                            <r.AnswerListItem key={sentence}>
                                <r.ResultText>&quot;{sentence}&quot;</r.ResultText>
                            </r.AnswerListItem>
                        );
                    })}
                </r.AnswerList>

                <r.ThankTextBox>
                    <r.HeaderSmall>아름다운 목소리를 들려주셔서 고마워요.</r.HeaderSmall>
                    <r.HeaderSmall>우리 함께 내쉬다로 더 연습해 볼까요?</r.HeaderSmall>
                    <r.RegistLink to="/signup">
                        <span>시작하기</span>
                        <ArrowCircleRightOutlinedIcon />
                    </r.RegistLink>
                    <r.Greet src={greet}></r.Greet>
                </r.ThankTextBox>
            </r.Box>
        </>
    );
}
