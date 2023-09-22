import React from "react";
import * as s from "./style";
import LocalPoliceIcon from "@mui/icons-material/LocalPolice";

export default function Answer() {
    const answer = {
        title: "테스트 타이틀",
        content:
            "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Aliquam vero sed dolores quis, molestiae libero sequi quibusdam mollitia rem, sit officia ullam eaque nemo, explicabo atque autem reprehenderit veniam facere!",
        regDate: "2021-10-10"
    };

    return (
        <>
            <s.AnswerBox>
                <s.SendFrom>
                    관리자
                    <LocalPoliceIcon />
                </s.SendFrom>
                <s.AnswerTitle>{answer.title}</s.AnswerTitle>
                <s.AnswerContent>{answer.content}</s.AnswerContent>
                <s.AnswerDate>{answer.regDate}</s.AnswerDate>
            </s.AnswerBox>
        </>
    );
}
