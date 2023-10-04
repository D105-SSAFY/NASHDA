import { useState, useEffect, useRef } from "react";

import Character from "components/mypage/user/statistics/statisticsDetail/character/Character";
import Conversation from "components/mypage/user/statistics/statisticsDetail/conversation/Conversation";
import Games from "components/mypage/user/statistics/statisticsDetail/games/Games";
import Weeks from "components/mypage/user/statistics/statisticsDetail/weeks/Weeks";

import { MoreButton, CloseButton } from "components/mypage/user/style";

import * as s from "./style";
import ArrowCircleDownIcon from "@mui/icons-material/ArrowCircleDown";
import ArrowCircleUpIcon from "@mui/icons-material/ArrowCircleUp";

export default function StatisticsDetail({ setMore, isToggle, setIsToggle }) {
    const [amount, setAmount] = useState(0);
    const [isChar, setIsChar] = useState(false);
    const [isConv, setIsConv] = useState(false);
    const [isWeek, setIsWeek] = useState(false);
    const [isGame, setIsGame] = useState(false);
    const charRef = useRef();
    const charSizeRef = useRef();
    const convRef = useRef();
    const convSizeRef = useRef();
    const weekRef = useRef();
    const weekSizeRef = useRef();
    const gameRef = useRef();
    const gameSizeRef = useRef();

    const setToggles = (idx, boolean) => {
        const temp = [...isToggle];
        temp[idx] = boolean;
        setIsToggle(temp);
    };

    const charToggle = () => {
        setToggles(0, !isToggle[0]);
        if (charRef.current.clientHeight === 250) {
            charRef.current.style.height = charSizeRef.current.clientHeight + "px";
        } else charRef.current.style.height = 250 + "px";
    };

    const convToggle = () => {
        setToggles(1, !isToggle[1]);
        if (convRef.current.clientHeight === 116) {
            convRef.current.style.height = convSizeRef.current.clientHeight + "px";
        } else convRef.current.style.height = 116 + "px";
    };

    const weekToggle = () => {
        setToggles(2, !isToggle[2]);
        if (weekRef.current.clientHeight === 260) {
            weekRef.current.style.height = weekSizeRef.current.clientHeight + "px";
        } else weekRef.current.style.height = 260 + "px";
    };

    const gameToggle = () => {
        setToggles(3, !isToggle[3]);
        if (gameRef.current.clientHeight === 190) {
            gameRef.current.style.height = gameSizeRef.current.clientHeight + "px";
        } else gameRef.current.style.height = 190 + "px";
    };

    useEffect(() => {
        if (!isToggle[0] && !isToggle[1] && !isToggle[2] && !isToggle[3] && true) {
            if (isChar) charRef.current.style.height = 250 + "px";
            if (isConv) convRef.current.style.height = 116 + "px";
            if (isGame) gameRef.current.style.height = 190 + "px";
        }
    }, [isToggle]);

    return (
        <s.StatDetailWrapper isChar={isChar} isConv={isConv} isWeek={isWeek} isGame={isGame} amount={amount}>
            <s.StatDetailCard ref={charRef}>
                <s.SizeWrapper ref={charSizeRef}>
                    <Character isChar={isChar} setIsChar={setIsChar} />
                    {isChar ? (
                        <MoreButton onClick={() => charToggle()}>
                            {isToggle[0] ? "접기" : "문자 정확도"}
                            {isToggle[0] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                        </MoreButton>
                    ) : null}
                </s.SizeWrapper>
            </s.StatDetailCard>
            <s.StatDetailCard ref={convRef}>
                <s.CardDivLine />
                <s.SizeWrapper ref={convSizeRef}>
                    <Conversation setAmount={setAmount} isConv={isConv} setIsConv={setIsConv} />
                    {isConv ? (
                        <MoreButton onClick={() => convToggle()}>
                            {isToggle[1] ? "접기" : "틀린 대화 내용"}
                            {isToggle[1] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                        </MoreButton>
                    ) : null}
                </s.SizeWrapper>
            </s.StatDetailCard>
            <s.StatDetailCard ref={weekRef}>
                <s.CardDivLine />
                <s.SizeWrapper ref={weekSizeRef}>
                    <Weeks isWeek={isWeek} setIsWeek={setIsWeek} />
                    {isWeek ? (
                        <MoreButton onClick={() => weekToggle()}>
                            {isToggle[2] ? "접기" : "주간 테스트 분석"}
                            {isToggle[2] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                        </MoreButton>
                    ) : null}
                </s.SizeWrapper>
            </s.StatDetailCard>
            <s.StatDetailCard ref={gameRef}>
                <s.CardDivLine />
                <s.SizeWrapper ref={gameSizeRef}>
                    <Games isToggle={isGame} isGame={isGame} setIsGame={setIsGame} />
                    {isGame ? (
                        <MoreButton onClick={() => gameToggle()}>
                            {isToggle[3] ? "접기" : "게임 플레이 결과"}
                            {isToggle[3] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                        </MoreButton>
                    ) : null}
                </s.SizeWrapper>
            </s.StatDetailCard>

            <CloseButton
                onClick={() => {
                    setMore(0);
                    setIsToggle([false, false, false, false]);
                }}
            />
        </s.StatDetailWrapper>
    );
}
