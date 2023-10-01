import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import eetch from "apis/eetch";

import * as s from "./style";

import Calendar from "./calendar/Calendar";

export default function StreakSection() {
    const user = useSelector((state) => state.user);
    const [stricks, setStricks] = useState({});
    const [until, setUntil] = useState("1997-09-21");

    const now = new Date();
    const utc = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
    const koreaTimeDiff = 9 * 60 * 60 * 1000;
    const korNow = new Date(utc + koreaTimeDiff);

    useEffect(() => {
        setUntil(korNow.toISOString().slice(0, 10));

        eetch.strick({ user }).then((res) => {
            const values = {};

            for (let i = 0; i < res.data.length; i++) {
                values[res.data[i].date] = res.data[i].total_count;
            }

            setStricks(values);
        });
    }, []);
    return (
        <s.Section>
            <s.Wrapper>
                <s.Header>
                    <h2>1년 사이 달성도</h2>
                </s.Header>
                <Calendar props={{ stricks, until }} />
            </s.Wrapper>
        </s.Section>
    );
}
