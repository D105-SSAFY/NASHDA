import React from "react";
import * as s from "./style";
import Question from "components/mypage/qna/question/Question";
import Answer from "components/mypage/qna/answer/Answer";

export default function Qna() {
    return (
        <>
            <s.QuestionBackground />
            <s.QnaSection>
                <Question />
                <Answer />
                <Question />
                <Answer />
                <Question />
                <Answer />
            </s.QnaSection>
            <s.QnaGradBox />
        </>
    );
}
