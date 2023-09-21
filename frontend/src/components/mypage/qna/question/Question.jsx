import React from "react";
import * as s from "./style";

// Import { questionGet } from "apis/question";

export default function Question({ index }) {
    // Const [question, setQuestion] = useState({});

    // setQuestion();
    const question = {
        title: "테스트 타이틀",
        content:
            "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Aliquam vero sed dolores quis, molestiae libero sequi quibusdam mollitia rem, sit officia ullam eaque nemo, explicabo atque autem reprehenderit veniam facere! uis, molestiae libero sequi quibusdam mollitia rem, sit officia ullam eaque nemo, explicabo atque autem reprehenderit veniam facere!",
        regDate: "2021-10-10"
    };
    console.log(index);
    return (
        <>
            <s.QuestionBox>
                <s.QuestionTitle>{question.title}</s.QuestionTitle>
                <s.QuestionContent>{question.content}</s.QuestionContent>
                <s.QuestionDate>{question.regDate}</s.QuestionDate>
            </s.QuestionBox>
        </>
    );
}
