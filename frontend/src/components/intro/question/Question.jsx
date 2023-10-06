import { useEffect, useRef, useState } from "react";
import SpeechRecognition, { useSpeechRecognition } from "react-speech-recognition";

import * as q from "./style";

import greet from "assets/image/greet2.png";
import MicIcon from "@mui/icons-material/Mic";

import Result from "components/intro/result/Result";

const questionTexts = ['"안녕하세요. 처음 만나 반갑습니다."', '"요즘 날씨가 많이 쌀쌀하네요."', '"앞으로도 잘 지내봐요."'];

export default function Intro() {
    const [result, setResult] = useState([]);
    const [step, setStep] = useState(0);
    const [isMicOn, setIsMicOn] = useState(false);
    const [canMic, setCanMic] = useState(true);

    const timerRef = useRef(null);

    const { transcript, resetTranscript, browserSupportsSpeechRecognition } = useSpeechRecognition();

    if (!browserSupportsSpeechRecognition) {
        setCanMic(true);
    }

    /** 테스트 용 토글 메소드 배포시 삭제 할 것
     * step, isMicOn toggle
     * */
    const nextStep = () => {
        setResult((result) => [...result, transcript]);
        resetTranscript();

        if (step < 3) {
            setStep(step + 1);
        }

        // if (step < 3) {
        //     SpeechRecognition.startListening({ language: "ko" });
        // }
    };

    const startRecognition = () => {
        if (canMic) {
            setIsMicOn(true);
        }

        SpeechRecognition.startListening({ continuous: true, language: "ko" });
    };

    // useEffect(() => {
    //     SpeechRecognition.stopListening();
    // }, [step]);

    useEffect(() => {
        if (!transcript) {
            return;
        }

        if (timerRef.current) {
            clearTimeout(timerRef.current);
        }

        timerRef.current = setTimeout(() => {
            nextStep();
        }, 2000);
    }, [transcript]);

    return (
        <>
            <q.Box visible={step !== 3} micOn={isMicOn}>
                <q.Header>
                    <q.HeaderSmall>처음만난 사람의 목소리를 듣는 것은</q.HeaderSmall>
                    <q.HeaderSmall>언제나 즐거운 일 입니다.</q.HeaderSmall>
                    <q.HeaderLarge>우리 반갑게 인사 해볼까요?</q.HeaderLarge>
                </q.Header>
                <q.Question micOn={isMicOn}>
                    <q.QuestionText>{questionTexts[step]}</q.QuestionText>
                    <MicIcon />
                    <q.MicButton visible={isMicOn}>지금 듣고 있어요!</q.MicButton>
                    <q.MicButton onClick={startRecognition} visible={!isMicOn}>
                        {canMic ? "여기를 눌러 마이크를 연결해주세요." : "마이크를 찾을 수 없어요."}
                    </q.MicButton>
                    <q.StepText>{step + 1} / 3</q.StepText>
                </q.Question>
                <q.Greet src={greet}></q.Greet>
            </q.Box>
            {step === 3 ? (
                <>
                    {/* {SpeechRecognition.stopListening()} */}
                    <Result props={{ origin: questionTexts, result }} />
                </>
            ) : (
                <></>
            )}
        </>
    );
}
