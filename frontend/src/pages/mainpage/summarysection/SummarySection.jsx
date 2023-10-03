import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import eetch from "apis/eetch";

import * as s from "./style";
export default function SummarySection() {
    const user = useSelector((state) => state.user);
    const [userName, setUserName] = useState("");
    const [strickDays, setStrickDays] = useState(0);
    const [history, setHistory] = useState([]);

    useEffect(() => {
        eetch.mypage({ user }).then((res) => {
            setUserName(res.data.nickname);
        });

        eetch.continuous({ user }).then((res) => {
            setStrickDays(res.data.continuous);
        });

        eetch.weekTest({ user }).then((res) => {
            setHistory([
                res.data.member_info.word_count.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","),
                res.data.member_info.sentence_count.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","),
                res.data.member_info.conversation_count.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
            ]);
        });
    }, []);

    return (
        <s.Section>
            <s.Header>
                <h2>다시 보아 너무 반갑네요!</h2>
                <p>
                    <s.NameLink to="/mypage">@{userName}</s.NameLink> 님의 연속격파 <span>{strickDays}일차</span>, 더 달려볼까요?
                </p>
            </s.Header>
            <s.TextWrapper>
                <p>
                    지금까지 <s.Words>단어 {history[0]}개</s.Words> / <s.Sentences>문장 {history[1]}개</s.Sentences> /{" "}
                    <s.Chats>대화 {history[2]}번</s.Chats> 완료
                </p>
                <p>* 아직 주간 시험에 응시하지 않으셨어요.</p>
            </s.TextWrapper>
        </s.Section>
    );
}
