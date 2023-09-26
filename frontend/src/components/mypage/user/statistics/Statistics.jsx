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

    const [totals, setTotals] = React.useState([0, 0, 0]);

    const total = React.useRef(totals);

    React.useEffect(() => {
        // 테스트용 타임 아웃
        setTimeout(() => {
            setScores([
                { score: 97, week: 15 },
                { score: 82, week: 14 },
                { score: 74, week: 13 }
            ]);
        }, 100);

        const interval = setInterval(() => {
            if (total.current[0] < 1000) {
                total.current[0] += 1;
                total.current[1] += 1;
                total.current[2] += 1;
                console.log(total.current);
                setTotals(total.current);
            }
        }, 10);

        console.log(interval);
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
            <MoreButton onClick={() => tabSwitch("통계")}>
                통계
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
