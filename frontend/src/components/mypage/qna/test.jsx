import { useState, useEffect, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import * as s from "./style";
import Question from "components/mypage/qna/question/Question";
import Answer from "components/mypage/qna/answer/Answer";

import eetch from "apis/eetch";

import SendIcon from "@mui/icons-material/Send";
import FilePresentIcon from "@mui/icons-material/FilePresent";

export default function Qna() {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [messages, setMessages] = useState([]);
    const [inputValue, setInputValue] = useState("");
    const [selectedFiles, setSelectedFiles] = useState([]);
    const fileInputRef = useRef();

    const handleChange = (e) => {
        setInputValue(e.target.value);
    };

    const handleFileChange = (event) => {
        setSelectedFiles(Array.from(event.target.files));
    };

    useEffect(() => {
        eetch.tokenValidation(eetch.questionList, { user }, dispatch).then((res) => {
            setMessages(res.data);
        });
    }, []);

    const handleSubmit = async () => {
        const formData = new FormData();

        selectedFiles.forEach((file, index) => {
            formData.append(`files[${index}]`, file);
        });

        try {
            // Replace "/upload" with your actual upload URL.
            await fetch("/upload", {
                method: "POST",
                body: formData
            });

            // Handle success case.
        } catch (error) {
            console.error(error);

            // Handle error case.
        }
    };

    const qnaList = messages.map((message, idx) => (
        <div key={idx * 3}>
            <Question key={idx * 3 + 1} question={message} />
            {message.reply ? <Answer key={idx * 3 + 2} answer={message.reply} /> : null}
        </div>
    ));

    return (
        <>
            <s.QuestionBackground />
            <s.QnaList>{qnaList}</s.QnaList>
            <s.AskBox>
                <s.TitleArea value={inputValue} onChange={handleChange} placeholder="질문 제목" />
                <s.ContentWrapper>
                    <s.ContentArea placeholder="질문 내용" />
                    <FilePresentIcon onClick={() => fileInputRef.current.click()} />
                    <input type="file" ref={fileInputRef} style={{ display: "none" }} onChange={handleFileChange} multiple />
                </s.ContentWrapper>
                <s.SubmitButton onClick={handleSubmit}>
                    <SendIcon />
                </s.SubmitButton>
            </s.AskBox>
            <s.QnaGradBox />
        </>
    );
}
