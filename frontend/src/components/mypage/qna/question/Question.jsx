import React from "react";
import * as s from "./style";

import AttachmentIcon from "@mui/icons-material/Attachment";

export default function Question({ question }) {
    const files = question.files.map((file, idx) => {
        return (
            <s.QuestionFile key={idx} href={file.url} download>
                <AttachmentIcon />
                {file.fileName}
            </s.QuestionFile>
        );
    });
    return (
        <>
            <s.QuestionBox>
                {question.url}
                <s.QuestionTitle>{question.title}</s.QuestionTitle>
                <s.QuestionContent>{question.content}</s.QuestionContent>
                {question.files.length > 0 ? <s.QuestionFileWrapper>{files}</s.QuestionFileWrapper> : null}
                <s.QuestionDate>{question.updateOn.substring(0, 10)}</s.QuestionDate>
                {question.reply ? null : <s.QuestionAnswer>미 답변</s.QuestionAnswer>}
            </s.QuestionBox>
        </>
    );
}
