import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import eetch from "apis/eetch";

import * as s from "./style";

import Calendar from "./calendar/Calendar";

export default function StreakSection() {
    const dispatch = useDispatch();

    const user = useSelector((state) => state.user);
    const [stricks, setStricks] = useState({});
    const [achievements, setAchievements] = useState([]);
    const [until, setUntil] = useState("1997-09-21");
    const [theme, setTheme] = useState("default");

    const now = new Date();
    const utc = now.getTime() + now.getTimezoneOffset() * 60 * 1000;
    const koreaTimeDiff = 9 * 60 * 60 * 1000;
    const korNow = new Date(utc + koreaTimeDiff);

    const changeTheme = (theme) => {
        window.localStorage.setItem("theme", theme);
        setTheme(theme);
    };

    useEffect(() => {
        setUntil(korNow.toISOString().slice(0, 10));

        eetch.tokenValidation(eetch.strick, { user }, dispatch).then((res) => {
            const values = {};

            for (let i = 0; i < res.data.length; i++) {
                values[res.data[i].date] = res.data[i].total_count;
            }

            setStricks(values);
        });

        eetch.tokenValidation(eetch.achievement, { user }, dispatch).then((res) => {
            setAchievements(res.data);
        });

        setTheme(window.localStorage.getItem("theme") ? window.localStorage.getItem("theme") : "default");
    }, []);

    return (
        <s.Section>
            <s.Wrapper>
                <s.Header theme={theme}>
                    <h2>1년 사이 달성도</h2>
                    <span>
                        <span onClick={() => changeTheme("default")}>기본</span>·<span onClick={() => changeTheme("fruit")}>과일</span>·
                        <span onClick={() => changeTheme("animal")}>동물</span>·<span onClick={() => changeTheme("ocean")}>바다</span>·
                        <span onClick={() => changeTheme("plant")}>초원</span>
                    </span>
                </s.Header>
                <Calendar theme={theme} props={{ achievements, stricks, until }} />
            </s.Wrapper>
        </s.Section>
    );
}
