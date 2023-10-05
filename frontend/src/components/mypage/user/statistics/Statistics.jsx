import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import * as s from "./style";
import { MoreButton } from "components/mypage/user/style";
import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

import eetch from "apis/eetch";

export default function Statistics({ more, setMore }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const [scores, setScores] = useState([
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null },
        { score: null, week: null }
    ]);

    const [totals, setTotals] = useState([0, 0, 0]);

    useEffect(() => {
        eetch.tokenValidation(eetch.weekTest, { user }, dispatch).then((res) => {
            setScores(res.data.test_info);
            setTotals([
                res.data.member_info.word_count.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","),
                res.data.member_info.sentence_count.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ","),
                res.data.member_info.conversation_count.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
            ]);
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
                <s.GraphInfoTitle more={more}>지금까지</s.GraphInfoTitle>
                <s.GraphInfoContent more={more}>단어 {totals[0]}</s.GraphInfoContent>
                <s.GraphInfoContent more={more}>문장 {totals[1]}</s.GraphInfoContent>
                <s.GraphInfoContent more={more}>대화 {totals[2]}</s.GraphInfoContent>
            </s.GraphInfo>
            <MoreButton onClick={() => setMore(5)}>
                통계
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
