import { useEffect, useRef, useState } from "react";

import * as s from "./style";

import VoiceSection from "./voicesection/VoiceSection";

import SmartToyIcon from "@mui/icons-material/SmartToy";
import PersonIcon from "@mui/icons-material/Person";

export default function ConversationSection() {
    const [convs, setConvs] = useState([]);
    const messageEndRef = useRef(null);

    useEffect(() => {
        // Fetch로 기본 문장 실행하기

        setConvs([
            {
                text: "안녕하세요",
                type: "user"
            },
            {
                text: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti!",
                type: "chatbot"
            },
            {
                text: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti!",
                type: "user"
            },
            {
                text: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti!",
                type: "chatbot"
            },
            {
                text: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti! Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti!",
                type: "user"
            },
            {
                text: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti!",
                type: "chatbot"
            },
            {
                text: "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Possimus, deleniti!",
                type: "user"
            }
        ]);
    }, []);

    const moveToEnd = () => {
        if (!messageEndRef.current) {
            return;
        }

        messageEndRef.current.scrollIntoView({ behavior: "smooth" });
    };

    useEffect(() => {
        moveToEnd();
    }, [convs]);

    return (
        <s.Section>
            <header>
                <h2>대화 시뮬레이션 영역</h2>
            </header>
            <s.ConversationList>
                {convs.map((conv, index) => {
                    return (
                        <li key={String(index) + conv.text}>
                            {conv.type === "chatbot" ? (
                                <s.BotTalker>
                                    <SmartToyIcon />
                                    <span>챗봇</span>
                                </s.BotTalker>
                            ) : (
                                <s.UserTalker>
                                    <PersonIcon />
                                    <span>사용자</span>
                                </s.UserTalker>
                            )}
                            {conv.type === "chatbot" ? <s.BotChat>{conv.text}</s.BotChat> : <s.MyChat>{conv.text}</s.MyChat>}
                        </li>
                    );
                })}
                <li>
                    <div ref={messageEndRef}></div>
                </li>
            </s.ConversationList>
            <VoiceSection props={{ moveToEnd, updateConvs: setConvs }} />
        </s.Section>
    );
}
