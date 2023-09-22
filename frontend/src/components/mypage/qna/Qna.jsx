import React, { useState, useRef } from "react";
import * as s from "./style";
import Question from "components/mypage/qna/question/Question";
import Answer from "components/mypage/qna/answer/Answer";

export default function Qna() {
    const [inputValue, setInputValue] = useState("");
    const timeoutIdRef = useRef(null);

    const handleChange = (e) => {
        setInputValue(e.target.value);

        if (timeoutIdRef.current) clearTimeout(timeoutIdRef.current);

        timeoutIdRef.current = setTimeout(() => {
            console.log(e.target.value);
        }, 300);
    };

    return (
        <>
            <s.QuestionBackground />
            <s.QnaList>
                <Question />
                <Answer />
                <Question />
                <Answer />
                <Question />
                <Answer />
                <Question />
                <Question />
                <Question />
                <Answer />
            </s.QnaList>
            <s.AskBox>
                <s.TitleArea value={inputValue} onChange={handleChange} placeholder="질문 제목" />
                <s.ContentArea placeholder="질문 내용" />
                <s.SubmitButton>질문하기</s.SubmitButton>
            </s.AskBox>
            <s.QnaGradBox />
        </>
    );
}
