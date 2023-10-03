import React from "react";
import { useSelector } from "react-redux";

import * as s from "./style";
import { MoreButton } from "components/mypage/user/style";
import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

import eetch from "apis/eetch";

export default function Statistics({ setMore }) {
    const user = useSelector((state) => state.user);

    const [scores, setScores] = React.useState([
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null }
    ]);

    const [totals, setTotals] = React.useState([0, 0, 0]);

    React.useEffect(() => {
        eetch.weekTest({ user }).then((res) => {
            setScores(res.data.test_info);
            setTotals([res.data.member_info.word_count, res.data.member_info.sentence_count, res.data.member_info.conversation_count]);
        });
    }, []);

    const graphs = () => {
        const result = [];
        for (let i = 0; i < 5; i++) {
            if (i < scores.length) {
                result.push(
                    <s.GraphBar key={i} index={i} score={scores[i].score}>
                        <s.GraphScore key={i}>{scores[i].score}</s.GraphScore>
                        <s.GraphWeek>{scores[i].week}주차</s.GraphWeek>
                    </s.GraphBar>
                );
            } else result.push(<s.GraphBall key={i} index={i} />);
        }

        return result;
    };

    return (
        <>
            <s.StatisticsTitle>주간 시험 · 점</s.StatisticsTitle>
            <s.Graph>
                {graphs()}
                <s.BottomLine />
            </s.Graph>
            <s.GraphInfo>
                <s.GraphInfoTitle>지금까지</s.GraphInfoTitle>
                <s.GraphInfoContent>단어 {totals[0]}</s.GraphInfoContent>
                <s.GraphInfoContent>문장 {totals[1]}</s.GraphInfoContent>
                <s.GraphInfoContent>대화 {totals[2]}</s.GraphInfoContent>
            </s.GraphInfo>
            <MoreButton onClick={() => setMore(5)}>
                통계
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
