import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import VoiceSection from "./voicesection/VoiceSection";

import SmartToyIcon from "@mui/icons-material/SmartToy";
import PersonIcon from "@mui/icons-material/Person";

import eetch from "apis/eetch";

const place = [
    {
        en: "cafe",
        kr: "카페",
        msg: "어서오세요. nashda카페입니다. 주문 도와드릴까요?"
    },
    {
        en: "police",
        kr: "경찰서",
        msg: "경찰서입니다. 무엇을 도와드릴까요?"
    },
    {
        en: "theater",
        kr: "영화관",
        msg: "어서오세요. nashda 극장입니다. 어떤 영화 예매를 도와드릴까요?"
    }
];

export default function ConversationSection() {
    const [background, setBackground] = useState({});
    const [id, setId] = useState("");
    const [convs, setConvs] = useState([]);
    const messageEndRef = useRef(null);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        setBackground(place[Math.floor(Math.random() * place.length)]);
    }, []);

    useEffect(() => {
        if (!background.en) {
            return;
        }

        const values = {};

        values.user = user;
        values.background = background.en;
        values.message = background.msg;

        eetch
            .tokenValidation(eetch.initSimulation, values, dispatch)
            .then((result) => {
                setId(result.data.id);
                setConvs([
                    {
                        type: "chatbot",
                        text: result.data.choices[0].message.content
                    }
                ]);
            })
            .catch(() => {
                navigate("/signin");
            });
    }, [background]);

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
            <s.Header>
                <h2>대화 시뮬레이션 영역</h2>
            </s.Header>
            {background.kr ? (
                <s.Explain>
                    저희는 지금, <span>{background.kr}</span>에 있습니다.
                </s.Explain>
            ) : (
                <></>
            )}
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
                            {conv.type !== "chatbot" && !conv.correct ? (
                                <s.Incorrect>
                                    <span>올바르지 않은 문장입니다. 다시 말해보세요.</span>
                                </s.Incorrect>
                            ) : (
                                <></>
                            )}
                        </li>
                    );
                })}
                <li>
                    <div ref={messageEndRef}></div>
                </li>
            </s.ConversationList>
            <VoiceSection props={{ moveToEnd, updateConvs: setConvs, id, background }} />
        </s.Section>
    );
}
