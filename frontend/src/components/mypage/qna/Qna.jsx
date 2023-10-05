import { useState, useEffect, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";
import * as s from "./style";
import Question from "components/mypage/qna/question/Question";
import Answer from "components/mypage/qna/answer/Answer";

import eetch from "apis/eetch";

import SendIcon from "@mui/icons-material/Send";
import FilePresentIcon from "@mui/icons-material/FilePresent";
import AttachmentIcon from "@mui/icons-material/Attachment";

export default function Qna() {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [messages, setMessages] = useState([]);
    const [inputTitle, setInputTitle] = useState("");
    const [inputContent, setInputContent] = useState("");
    const [selectedFiles, setSelectedFiles] = useState([]);
    const fileInputRef = useRef();

    const [isUploading, setIsUploading] = useState(false);

    const handleTitleChange = (e) => {
        setInputTitle(e.target.value);
    };

    const handleContentChange = (e) => {
        setInputContent(e.target.value);
    };

    const handleFileChange = (event) => {
        setSelectedFiles(Array.from(event.target.files));

        console.log(selectedFiles);
    };

    useEffect(() => {
        eetch.tokenValidation(eetch.questionList, { user }, dispatch).then((res) => {
            setMessages(res.data);
        });
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();

        if (isUploading) {
            console.log("stop!!");
        } else {
            setIsUploading(true);

            const formData = new FormData();

            formData.append("title", inputTitle);
            formData.append("content", inputContent);

            selectedFiles.forEach((file) => {
                formData.append(`files`, file);
            });

            eetch.tokenValidation(eetch.questionWrite, { formData, user }, dispatch).then(() => {
                eetch.tokenValidation(eetch.questionList, { user }, dispatch).then((res) => {
                    setMessages(res.data);
                    setInputTitle("");
                    setInputContent("");
                    setSelectedFiles([]);

                    setIsUploading(false);
                });
            });
        }
    };

    const qnaList = messages.map((message, idx) => (
        <div key={idx * 3}>
            <Question key={idx * 3 + 1} question={message} />
            {message.reply ? <Answer key={idx * 3 + 2} answer={message.reply} /> : null}
        </div>
    ));

    const fileList = selectedFiles.map((file, idx) => (
        <li key={idx}>
            <AttachmentIcon />
            {file.name}
        </li>
    ));

    return (
        <>
            <s.QuestionBackground />
            <s.QnaList>{qnaList}</s.QnaList>
            <s.AskBox>
                <s.TitleArea value={inputTitle} onChange={handleTitleChange} placeholder="질문 제목" />
                <s.ContentWrapper>
                    <s.ContentArea value={inputContent} onChange={handleContentChange} selected={selectedFiles.length} placeholder="질문 내용" />
                    <FilePresentIcon onClick={() => fileInputRef.current.click()} />
                    <input type="file" ref={fileInputRef} style={{ display: "none" }} onChange={handleFileChange} multiple />
                    <s.SelectedFiles>{fileList}</s.SelectedFiles>
                </s.ContentWrapper>
                <s.SubmitButton onClick={handleSubmit}>
                    <SendIcon />
                </s.SubmitButton>
            </s.AskBox>
            <s.QnaGradBox />
        </>
    );
}
