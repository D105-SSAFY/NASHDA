import { useState } from "react";

import Character from "components/mypage/user/statistics/statisticsDetail/character/Character";
import Conversation from "components/mypage/user/statistics/statisticsDetail/conversation/Conversation";
import Games from "components/mypage/user/statistics/statisticsDetail/games/Games";

import { MoreButton, CloseButton } from "components/mypage/user/style";

import * as s from "./style";
import ArrowCircleDownIcon from "@mui/icons-material/ArrowCircleDown";
import ArrowCircleUpIcon from "@mui/icons-material/ArrowCircleUp";

export default function StatisticsDetail({ setMore, isToggle, setIsToggle }) {
    const [isData, setIsData] = useState([false, false, true]);
    const setDatas = (idx, boolean) => {
        const temp = [...isData];
        temp[idx] = boolean;
        setIsData(temp);
    };

    const setToggles = (idx, boolean) => {
        const temp = [...isToggle];
        temp[idx] = boolean;
        setIsToggle(temp);
    };

    const [amount, setAmount] = useState(0);

    return (
        <s.StatDetailWrapper amount={amount} isData={isData} isToggle={isToggle}>
            <s.StatDetailCard>
                <Character setDatas={setDatas} />
                {isData[0] ? (
                    <MoreButton onClick={() => setToggles(0, !isToggle[0])}>
                        {isToggle[0] ? "접기" : "문자 정확도"}
                        {isToggle[0] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                    </MoreButton>
                ) : null}

                <s.CardDivLine />
            </s.StatDetailCard>
            <s.StatDetailCard>
                <Conversation setAmount={setAmount} setDatas={setDatas} />
                {isData[1] ? (
                    <MoreButton onClick={() => setToggles(1, !isToggle[1])}>
                        {isToggle[1] ? "접기" : "틀린 대화 내용"}
                        {isToggle[1] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                    </MoreButton>
                ) : null}

                <s.CardDivLine />
            </s.StatDetailCard>
            <s.StatDetailCard>
                <Games setDatas={setDatas} />
                {isData[2] ? (
                    <MoreButton onClick={() => setToggles(2, !isToggle[2])}>
                        {isToggle[2] ? "접기" : "틀린 대화 내용"}
                        {isToggle[2] ? <ArrowCircleUpIcon /> : <ArrowCircleDownIcon />}
                    </MoreButton>
                ) : null}
            </s.StatDetailCard>

            <CloseButton
                onClick={() => {
                    setMore(0);
                    setIsToggle([false, false, false]);
                }}
            />
        </s.StatDetailWrapper>
    );
}
