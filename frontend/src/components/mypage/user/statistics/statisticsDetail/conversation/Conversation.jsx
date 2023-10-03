import { useState, useEffect } from "react";
import { useSelector } from "react-redux";

import eetch from "apis/eetch";

import * as c from "./style";
import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
import SmartToyIcon from "@mui/icons-material/SmartToy";

export default function Conversation({ setDatas, setAmount }) {
    const user = useSelector((state) => state.user);
    const [totalIncorrect, setTotalIncorrect] = useState(-1);
    // const [conversations, setConversations] = useState([]);
    const [isCafe, setIsCafe] = useState(false);
    const [isPolice, setIsPolice] = useState(false);
    const [isTheater, setIsTheater] = useState(false);
    const [Cafe, setCafe] = useState([]);
    const [Police, setPolice] = useState([]);
    const [Theater, setTheater] = useState([]);

    const makeChat = (type) => {
        const temp = [];
        switch (type) {
            case "cafe":
                Cafe.forEach((obj, idx) => {
                    temp.push(
                        <div key={idx}>
                            <c.MyChat>{obj.my_answer}</c.MyChat>
                            <c.AiChat>
                                <SmartToyIcon />
                                {obj.ai_response}
                            </c.AiChat>
                        </div>
                    );
                });
                break;
            case "police":
                Police.forEach((obj, idx) => {
                    temp.push(
                        <div key={idx}>
                            <c.MyChat>{obj.my_answer}</c.MyChat>
                            <c.AiChat>
                                <SmartToyIcon />
                                {obj.ai_response}
                            </c.AiChat>
                        </div>
                    );
                });
                break;
            case "theater":
                Theater.forEach((obj, idx) => {
                    temp.push(
                        <div key={idx}>
                            <c.MyChat>{obj.my_answer}</c.MyChat>
                            <c.AiChat>
                                <SmartToyIcon />
                                {obj.ai_response}
                            </c.AiChat>
                        </div>
                    );
                });
                break;
            default:
                temp.push(<div>none</div>);
        }

        return temp;
    };

    useEffect(() => {
        eetch.practiceSimul({ user }).then((res) => {
            // setConversations(res.data);
            if (res.data) {
                setTotalIncorrect(res.data.reduce((acc, cur) => acc + cur.total - cur.score, 0));
            }

            res.data.forEach((obj) => {
                if (obj.background === "cafe") setIsCafe(true);
                if (obj.background === "police") setIsPolice(true);
                if (obj.background === "theater") setIsTheater(true);
            });

            setAmount(res.data.length);
        });

        eetch.practiceSimulBackground({ background: "cafe", user }).then((res) => {
            setCafe(res.data);
        });

        eetch.practiceSimulBackground({ background: "police", user }).then((res) => {
            setPolice(res.data);
        });

        eetch.practiceSimulBackground({ background: "theater", user }).then((res) => {
            setTheater(res.data);
        });
    }, []);

    useEffect(() => {
        if (totalIncorrect > 0) {
            setDatas(1, true);
        }
    }, [totalIncorrect]);

    return (
        <>
            <c.ConversationTitle>
                {totalIncorrect === -1 ? (
                    <c.NoDataWrapper>
                        <c.NoData>내쉬다와 자연스러운 대화 어때요?</c.NoData>
                        <c.NoDataLink>
                            AI 대화
                            <ArrowCircleRightOutlinedIcon />
                        </c.NoDataLink>
                    </c.NoDataWrapper>
                ) : totalIncorrect === 0 ? (
                    <c.InfoWrapper>
                        <c.InfoTitle>대화의 달인이군요? AI와의 대화에서 어색했던 적이 없어요.</c.InfoTitle>
                    </c.InfoWrapper>
                ) : (
                    <c.InfoWrapper>
                        <c.InfoTitle>AI와의 대화 중 총 {totalIncorrect}번의 어색한 대화가 있었어요.</c.InfoTitle>
                    </c.InfoWrapper>
                )}{" "}
            </c.ConversationTitle>
            {totalIncorrect > 0 ? (
                <>
                    {isCafe ? (
                        <>
                            <c.TypeTitle>카페</c.TypeTitle>
                            <c.ChatBox>{makeChat("cafe")}</c.ChatBox>
                        </>
                    ) : null}
                    {isPolice ? (
                        <>
                            <c.TypeTitle>경찰서</c.TypeTitle>
                            <c.ChatBox>{makeChat("police")}</c.ChatBox>
                        </>
                    ) : null}
                    {isTheater ? (
                        <>
                            <c.TypeTitle>영화관</c.TypeTitle>
                            <c.ChatBox>{makeChat("theater")}</c.ChatBox>
                        </>
                    ) : null}
                </>
            ) : null}
        </>
    );
}
