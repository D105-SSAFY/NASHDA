import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import eetch from "apis/eetch";

import * as w from "./style";

import SelectInput from "components/input/FormSelectCol";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
import TipsAndUpdatesIcon from "@mui/icons-material/TipsAndUpdates";
// import UnfoldMoreIcon from "@mui/icons-material/UnfoldMore";

export default function Weeks({ isWeek, setIsWeek }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const [weeks, setWeeks] = useState([]);
    const [weeksSliced, setWeeksSliced] = useState([]);
    const [weekSelected, setWeekSelected] = useState(0);
    const [weekExists, setWeekExists] = useState([]);
    const [weekDetails, setWeekDetails] = useState([]);

    useEffect(() => {
        eetch.tokenValidation(eetch.weekTestAll, { user }, dispatch).then((res) => {
            const arr = [];
            for (const key in res.data.scores) {
                if (res.data.scores[key]) {
                    arr.push({ week: key, ...res.data.scores[key] });
                }
            }

            setWeeks(arr);
            setWeeksSliced(arr.slice(weeks.length - 10, weeks.length));
        });

        eetch.tokenValidation(eetch.weekTestDetail, { user }, dispatch).then((res) => {
            setWeekDetails(res.data);

            const newWeekExists = [];
            res.data.forEach((obj, idx) => {
                newWeekExists.push({ hobbyIdx: idx, hobby: obj.week + "주차 " + obj.try_count + "차 테스트" });
            });

            setWeekExists(newWeekExists);
        });
    }, []);

    useEffect(() => {
        if (weeks.length !== 0) {
            setIsWeek(true);
            setWeekSelected(1);
            setWeeksSliced(weeks.slice(weeks.length - 10, weeks.length));
        }
    }, [weeks]);

    const changeWeek = (target, idx) => {
        if (target === "취미") setWeekSelected(idx);
        console.log(idx, weekSelected);
    };

    const weeksHeader = () => {
        const header = [];
        if (weeksSliced.length !== 0) {
            header.push(<th key={0}></th>);
            weeksSliced.forEach((obj, idx) => {
                header.push(<th key={idx + 1}>{obj.week}주차</th>);
            });
        }

        return header;
    };

    const weeksBody = () => {
        const body = [];
        if (weeksSliced.length !== 0) {
            const maxValues = weeksSliced.map((week) => Math.max(...Object.values(week).filter((item) => typeof item === "number")));
            for (let i = 0; i < 3; i++) {
                body.push(
                    <tr key={i}>
                        <td>{i + 1}차</td>
                        {weeksSliced.map((obj, idx) => (
                            <td key={idx + 1} style={{ color: obj[i] === maxValues[idx] ? "#6446ff" : "#000" }}>
                                {obj[i] || "-"}
                            </td>
                        ))}
                    </tr>
                );
            }
        }

        return body;
    };

    const weekDetailsContent = () => {
        const content = [];
        if (weekDetails.length !== 0) {
            const blank = weekDetails[weekSelected].blankTest;

            console.log(blank);
            content.push(
                <w.weekDetailWrapper key={0}>
                    <w.weekBlankWrapper>
                        <w.weekBlankItem>
                            <w.BlankImg src={blank[0].imgURL} />
                            <w.BlankAnswer>&quot;{blank[0].answer}&quot;</w.BlankAnswer>
                            <w.BlankHint>
                                힌트
                                <TipsAndUpdatesIcon />
                            </w.BlankHint>
                            <w.HintWrapper>
                                {blank[0].hint.map((text, idx) => (
                                    <w.Hint key={idx}>
                                        {idx + 1}. {text}
                                    </w.Hint>
                                ))}
                            </w.HintWrapper>
                        </w.weekBlankItem>
                        <w.weekBlankItem>
                            <w.BlankImg src={blank[1].imgURL} />
                            <w.BlankAnswer>&quot;{blank[1].answer}&quot;</w.BlankAnswer>
                            <w.BlankHint>힌트</w.BlankHint>
                        </w.weekBlankItem>
                        <w.weekBlankItem>
                            <w.BlankImg src={blank[2].imgURL} />
                            <w.BlankAnswer>&quot;{blank[2].answer}&quot;</w.BlankAnswer>
                        </w.weekBlankItem>
                        <w.weekBlankItem>
                            <w.BlankImg src={blank[3].imgURL} />
                            <w.BlankAnswer>&quot;{blank[3].answer}&quot;</w.BlankAnswer>
                        </w.weekBlankItem>
                    </w.weekBlankWrapper>
                </w.weekDetailWrapper>
            );
        }

        return content;
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
                                label: "테스트 주차 및 차수",
                                target: "취미",
                                list: weekExists,
                                callback: changeWeek,
                                idx: weekSelected
                            }}
                        />
                        {weekDetails === 0 ? null : weekDetailsContent()}
                    </w.WeeksResultWrapper>
                </>
            )}
        </w.WeekWrapper>
    );
}
