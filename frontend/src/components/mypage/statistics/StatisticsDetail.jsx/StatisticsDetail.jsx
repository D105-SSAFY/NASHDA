import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";

import eetch from "apis/eetch";

export default function StatisticsDetail() {
    const user = useSelector((state) => state.user);

    const [stat, setStat] = useState({});

    useEffect(() => {
        eetch.games({ user }).then((res) => {
            console.log(res);
        });

        eetch.gameBlankWeek({ week: 4, user }).then((res) => {
            console.log(res);
        });

        eetch.gameSpeedWeek({ week: 5, user }).then((res) => {
            console.log(res);
        });

        eetch.practiceWord({ user }).then((res) => {
            console.log(res);
        });

        eetch.practiceSimul({ user }).then((res) => {
            console.log(res);
        });

        eetch.practiceSimulBackground({ background: "cafe", user }).then((res) => {
            console.log(res);
        });

        setStat({});
        console.log(stat);
    }, []);
    return <div>StatisticsDetail</div>;
}
