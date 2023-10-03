import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import eetch from "apis/eetch";

import * as w from "./style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Weeks({ setIsWeek }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);

    const [weeks, setWeeks] = useState([]);

    useEffect(() => {
        eetch.tokenValidation(eetch.weekTestAll, { user }, dispatch).then((res) => {
            const arr = [];
            for (const key in res.data.scores) {
                if (res.data.scores[key]) {
                    arr.push({ week: key, ...res.data.scores[key] });
                }
            }

            setWeeks(arr);
        });
    }, []);

    useEffect(() => {
        if (weeks.length !== 0) {
            setIsWeek(true);
        }
    }, [weeks]);

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
        <div>
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
                </>
            )}
        </div>
    );
}
