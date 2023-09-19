import React, { useState } from "react";
import * as q from "./style";

import greet from "assets/image/greet2.png";
import MicIcon from "@mui/icons-material/Mic";

import Result from "components/intro/result/Result";

export default function Intro() {
    // Const [isLogin, setIsLogin] = React.useState(false);
    const [step, setStep] = useState(0);
    const [isMicOn, setIsMicOn] = useState(false);

    /** 테스트 용 토글 메소드 배포시 삭제 할 것
     * step, isMicOn toggle
     * */
    const nextStep = () => {
        if (step < 3) setStep(step + 1);
        setIsMicOn(!isMicOn);
    };

    const questionTexts = ['"안녕하세요. 처음 만나 반갑습니다."', '"안녕하세요. 처음 만나 반갑습니다.2"', '"안녕하세요. 처음 만나 반갑습니다.3"'];

    return (
        <>
            <q.Box visible={step !== 3}>
                <button onClick={nextStep}>테스트</button>
                <q.Header>
                    <q.HeaderSmall>처음만난 사람의 목소리를 듣는 것은</q.HeaderSmall>
                    <q.HeaderSmall>언제나 즐거운 일 입니다.</q.HeaderSmall>
                    <q.HeaderLarge>우리 반갑게 인사 해볼까요?</q.HeaderLarge>
                </q.Header>
                <q.Question>
                    <q.QuestionText>{questionTexts[step]}</q.QuestionText>
                    <MicIcon />
                    <q.MicText visible={isMicOn}>지금 듣고 있어요!</q.MicText>
                    <q.MicText visible={!isMicOn}>연결된 마이크가 없어요.</q.MicText>
                    <q.StepText>{step + 1} / 3</q.StepText>
                </q.Question>
                <q.Greet src={greet}></q.Greet>
            </q.Box>
            {step === 3 ? <Result /> : <></>}
        </>
    );
}
