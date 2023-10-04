import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import eetch from "apis/eetch";

import * as c from "./style";

import SelectInput from "components/input/FormSelectCol";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
import SmartToyIcon from "@mui/icons-material/SmartToy";

export default function Conversation({ isConv, setIsConv, setAmount }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [totalIncorrect, setTotalIncorrect] = useState(-1);
    // const [conversations, setConversations] = useState([]);
    const [Cafe, setCafe] = useState([]);
    const [Police, setPolice] = useState([]);
    const [Theater, setTheater] = useState([]);
    const [place, setPlace] = useState(0);
    const [places, setPlaces] = useState([]);

    const changePlace = (target, idx) => {
        if (target === "취미") setPlace(idx);
    };

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
        eetch.tokenValidation(eetch.practiceSimul, { user }, dispatch).then((res) => {
            if (res.data) {
                if (res.data) {
                    setTotalIncorrect(res.data.reduce((acc, cur) => acc + cur.total - cur.score, 0));
                }

                res.data.forEach((obj) => {
                    if (obj.background === "cafe") {
                        places.push({ hobbyIdx: 1, hobby: "카페" });
                        setPlaces(places);
                    }

                    if (obj.background === "police") {
                        places.push({ hobbyIdx: 2, hobby: "경찰서" });
                        setPlaces(places);
                    }

                    if (obj.background === "theater") {
                        places.push({ hobbyIdx: 3, hobby: "영화관" });
                        setPlaces(places);
                    }
                });

                setAmount(res.data.length);
            }
        });

        eetch.tokenValidation(eetch.practiceSimulBackground, { background: "cafe", user }, dispatch).then((res) => {
            setCafe(res.data);
        });

        eetch.tokenValidation(eetch.practiceSimulBackground, { background: "police", user }, dispatch).then((res) => {
            setPolice(res.data);
        });

        eetch.tokenValidation(eetch.practiceSimulBackground, { background: "theater", user }, dispatch).then((res) => {
            setTheater(res.data);
        });
    }, []);

    useEffect(() => {
        if (totalIncorrect > 0) {
            setIsConv(true);
            setPlace(1);
        }
    }, [totalIncorrect]);

    return (
        <c.ConversationWrapper isConv={isConv}>
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
                )}
            </c.ConversationTitle>
            {totalIncorrect > 0 ? (
                <>
                    <SelectInput
                        data={{
                            label: "장소",
                            target: "취미",
                            list: places,
                            callback: changePlace,
                            Idx: place
                        }}
                    />
                    {place === 1 ? (
                        <>
                            <c.ChatBox>{makeChat("cafe")}</c.ChatBox>
                        </>
                    ) : null}
                    {place === 2 ? (
                        <>
                            <c.ChatBox>{makeChat("police")}</c.ChatBox>
                        </>
                    ) : null}
                    {place === 3 ? (
                        <>
                            <c.ChatBox>{makeChat("theater")}</c.ChatBox>
                        </>
                    ) : null}
                </>
            ) : null}
        </c.ConversationWrapper>
    );
}
