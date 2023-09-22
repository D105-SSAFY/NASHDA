import React from "react";
import * as u from "./style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Setting() {
    const userName = "Consolas";
    const [more, setMore] = React.useState(0);

    const getMore = (n) => {
        setMore(n);
    };

    const scores = [
        { score: 97, week: 15 },
        { score: 82, week: 14 },
        { score: 74, week: 13 }
    ];
    const total = [12854, 2480, 711];
    console.log(scores);

    const graphs = () => {
        const result = [];
        for (let i = 0; i < 5; i++) {
            if (i < scores.length) {
                result.push(
                    <u.GraphBar index={i} score={scores[i].score}>
                        <u.GraphScore>{scores[i].score}</u.GraphScore>
                        <u.GraphWeek>{scores[i].week}주차</u.GraphWeek>
                    </u.GraphBar>
                );
            } else result.push(<u.GraphBall index={i} />);
        }

        return result;
    };

    return (
        <>
            <u.DeFocusTouch onClick={() => getMore(0)} />
            <u.UserSection>
                <u.UserCard focus={more === 1} defocus={more === 0 || more === 1}>
                    <u.ProfileName>@{userName}</u.ProfileName>
                    <u.MoreButton onClick={() => getMore(1)}>
                        별명 변경
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard focus={more === 2} defocus={more === 0 || more === 2}>
                    <u.StatisticsTitle>주간 시험 · 점</u.StatisticsTitle>
                    <u.Graph>
                        {graphs()}
                        <u.BottomLine />
                    </u.Graph>
                    <u.GraphInfo>
                        <u.GraphInfoTitle>지금까지</u.GraphInfoTitle>
                        <u.GraphInfoContent>단어 {total[0]}</u.GraphInfoContent>
                        <u.GraphInfoContent>문장 {total[1]}</u.GraphInfoContent>
                        <u.GraphInfoContent>대화 {total[2]}</u.GraphInfoContent>
                    </u.GraphInfo>
                    <u.MoreButton onClick={() => getMore(2)}>
                        통계
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard focus={more === 3} defocus={more === 0 || more === 3}>
                    <u.MoreButton onClick={() => getMore(3)}>
                        달성
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard focus={more === 4} defocus={more === 0 || more === 4}>
                    <u.MoreButton onClick={() => getMore(4)}>
                        상세 프로필
                        <ArrowCircleRightOutlinedIcon />
                    </u.MoreButton>
                </u.UserCard>
                <u.UserCard onClick={() => getMore(0)} />
                focused on : {more}
            </u.UserSection>
        </>
    );
}
