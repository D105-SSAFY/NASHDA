import React from "react";
import * as s from "./style";
import { MoreButton } from "components/mypage/user/style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Statistics({ tabSwitch }) {
    const [scores, setScores] = React.useState([
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null }
    ]);

    setTimeout(() => {
        setScores([
            { score: 97, week: 15 },
            { score: 82, week: 14 },
            { score: 74, week: 13 }
        ]);
    }, 100);
    const total = [12854, 2480, 711];

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
                <s.GraphInfoContent>단어 {total[0]}</s.GraphInfoContent>
                <s.GraphInfoContent>문장 {total[1]}</s.GraphInfoContent>
                <s.GraphInfoContent>대화 {total[2]}</s.GraphInfoContent>
            </s.GraphInfo>
            <MoreButton onClick={() => tabSwitch("통계")}>
                통계
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
