import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import eetch from "apis/eetch";

import * as w from "./style";

import SelectInput from "components/input/FormSelectCol";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
// import UnfoldMoreIcon from "@mui/icons-material/UnfoldMore";

export default function Weeks({ isWeek, setIsWeek }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const [weeks, setWeeks] = useState([]);
    const [slicedWeeks, setSlicedWeeks] = useState([]);
    const [recentFirst, setRecentFirst] = useState([]);
    const [recentSecond, setRecentSecond] = useState([]);
    const [recentThird, setRecentThird] = useState([]);

    const [weekExists, setWeekExists] = useState([]);
    const [weekSelected, setWeekSelected] = useState(0);

    useEffect(() => {
        eetch.tokenValidation(eetch.weekTestAll, { user }, dispatch).then((res) => {
            const arr = [];
            for (const key in res.data.scores) {
                if (res.data.scores[key]) {
                    arr.push({ week: key, ...res.data.scores[key] });
                }
            }

            setWeeks(arr);
            setSlicedWeeks(arr.slice(arr.length - 3, arr.length));
        });
    }, []);

    useEffect(() => {
        if (weeks.length !== 0) {
            setIsWeek(true);
        }
    }, [weeks]);

    useEffect(() => {
        if (slicedWeeks.length !== 0) {
            const tempWeekExists = [...weekExists];
            if (slicedWeeks.length - 1 >= 0)
                eetch.tokenValidation(eetch.weekTestDetail, { week: slicedWeeks[slicedWeeks.length - 1].week, user }, dispatch).then((res) => {
                    setRecentFirst([slicedWeeks[slicedWeeks.length - 1].week, ...res.data]);
                    for (let i = 0; i < res.data.length; i++) {
                        tempWeekExists.push({ hobbyIdx: i, hobby: slicedWeeks[slicedWeeks.length - 1].week + "주차 " + (i + 1) + "차 테스트" });
                    }
                });

            if (slicedWeeks.length - 2 >= 0)
                eetch.tokenValidation(eetch.weekTestDetail, { week: slicedWeeks[slicedWeeks.length - 2].week, user }, dispatch).then((res) => {
                    setRecentSecond(res.data);
                    for (let i = 0; i < res.data.length; i++) {
                        tempWeekExists.push({ hobbyIdx: i + 3, hobby: slicedWeeks[slicedWeeks.length - 2].week + "주차 " + (i + 1) + "차 테스트" });
                    }
                });

            if (slicedWeeks.length - 3 >= 0)
                eetch.tokenValidation(eetch.weekTestDetail, { week: slicedWeeks[slicedWeeks.length - 3].week, user }, dispatch).then((res) => {
                    setRecentThird(res.data);
                    for (let i = 0; i < res.data.length; i++) {
                        tempWeekExists.push({ hobbyIdx: i + 6, hobby: slicedWeeks[slicedWeeks.length - 3].week + "주차 " + (i + 1) + "차 테스트" });
                    }
                });

            const sorted = tempWeekExists.sort((a, b) => a.hobbyIdx - b.hobbyIdx);
            setWeekExists(sorted);
        }
    }, [slicedWeeks]);

    useEffect(() => {
        console.log(weekExists);
    }, [weekExists]);

    const changeWeek = (target, idx) => {
        if (target === "취미") setWeekSelected(idx);
        console.log(idx, weekSelected);
    };

    const weeksHeader = () => {
        const header = [];
        if (weeks.length !== 0) {
            const sliced = weeks.slice(weeks.length - 10, weeks.length);
            header.push(<th key={0}></th>);
            sliced.forEach((obj, idx) => {
                header.push(<th key={idx + 1}>{obj.week}주차</th>);
            });

            return header;
        }

        return null;
    };

    const weeksBody = () => {
        const body = [];
        if (weeks.length !== 0) {
            const sliced = weeks.slice(weeks.length - 10, weeks.length);
            const maxValues = sliced.map((week) => Math.max(...Object.values(week).filter((item) => typeof item === "number")));
            for (let i = 0; i < 3; i++) {
                body.push(
                    <tr key={i}>
                        <td>{i + 1}차</td>
                        {sliced.map((obj, idx) => (
                            <td key={idx + 1} style={{ color: obj[i] === maxValues[idx] ? "#6446ff" : "#000" }}>
                                {obj[i] || "-"}
                            </td>
                        ))}
                    </tr>
                );
            }

            return body;
        }

        return null;
    };

    return (
        <w.WeekWrapper isWeek={isWeek}>
            {weeks.length === 0 ? (
                <w.NoDataWrapper>
                    <w.NoData>아직 한번도 주간 테스트에 응시하지 않았어요.</w.NoData>
                    <w.NoDataLink>
                        테스트 응시
                        <ArrowCircleRightOutlinedIcon />
                    </w.NoDataLink>
                </w.NoDataWrapper>
            ) : (
                <>
                    <w.StatTable>
                        <w.StatTableHead>
                            <tr>{weeksHeader()}</tr>
                        </w.StatTableHead>
                        <w.StatTableBody>{weeksBody()}</w.StatTableBody>
                    </w.StatTable>
                    <w.WeeksResultWrapper>
                        <SelectInput
                            data={{
                                target: "취미",
                                list: weekExists,
                                callback: changeWeek
                            }}
                        />
                        {weekSelected === 0 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 1 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 2 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 3 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 4 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 5 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 6 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 7 ? <div>{weekSelected}</div> : null}
                        {weekSelected === 8 ? <div>{weekSelected}</div> : null}
                        {recentFirst.length === recentSecond.length && recentSecond.length === recentThird.length ? null : null}
                    </w.WeeksResultWrapper>
                </>
            )}
        </w.WeekWrapper>
    );
}
