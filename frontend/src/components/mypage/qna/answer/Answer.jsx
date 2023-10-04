import React from "react";
import * as s from "./style";
import LocalPoliceIcon from "@mui/icons-material/LocalPolice";

export default function Answer({ answer }) {
    return (
        <>
            <s.AnswerBox>
                <s.SendFrom>
                    관리자
                    <LocalPoliceIcon />
                </s.SendFrom>
                <s.AnswerTitle>{answer.title}</s.AnswerTitle>
                <s.AnswerContent>{answer.content}</s.AnswerContent>
                <s.AnswerDate>{answer.updateOn.substring(0, 10)}</s.AnswerDate>
            </s.AnswerBox>
        </>
    );
}
