import dayjs from "dayjs";
import React, { useState } from "react";
import Measure from "react-measure";

// Const weekNames = ["일", "월", "화", "수", "목", "금", "토"];
const weekNames = ["일", "월", "화", " ", " ", " ", " "];
const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
const panelColors = ["#EEE", "rgba(100, 172, 239, 0.6)", "rgba(100, 172, 239, 0.86)", "rgba(100, 172, 239, 1)"];
const dateFormat = "YYYY-MM-DD";

export default function Calendar({ props }) {
    const monthLabelHeight = 15;
    const weekLabelWidth = 15;
    const panelSize = 13;
    const panelMargin = 2;
    const [state, setState] = useState({
        columns: 53,
        maxWidth: 53
    });

    const getPanelPosition = (row, col) => {
        const bounds = panelSize + panelMargin;

        return {
            x: weekLabelWidth + bounds * row,
            y: monthLabelHeight + bounds * col
        };
    };

    const makeCalendarData = (history, lastDay, columns) => {
        const d = dayjs(lastDay, { format: dateFormat });
        const lastWeekend = d.endOf("week");
        const endDate = d.endOf("day");

        const result = [];

        for (let i = 0; i < columns; i++) {
            result[i] = [];

            for (let j = 0; j < 7; j++) {
                const date = lastWeekend.subtract((columns - i - 1) * 7 + (6 - j), "day");

                if (date <= endDate) {
                    result[i][j] = {
                        value: history[date.format(dateFormat)] || 0,
                        month: date.month()
                    };
                } else {
                    result[i][j] = null;
                }
            }
        }

        return result;
    };

    const updateSize = (size) => {
        if (!size) {
            return;
        }

        const visibleWeeks = Math.floor((size.width - weekLabelWidth) / 13);

        setState({
            ...state,
            columns: Math.min(visibleWeeks, state.maxWidth)
        });
    };

    const { columns } = state;
    const { values } = props;
    const { until } = props;

    if (panelColors === undefined || weekNames === undefined || monthNames === undefined) {
        return;
    }

    const contributions = makeCalendarData(values, until, columns);
    const innerDom = [];

    for (let i = 0; i < columns; i++) {
        for (let j = 0; j < 7; j++) {
            const contribution = contributions[i][j];

            if (contribution === null) {
                continue;
            }

            const pos = getPanelPosition(i, j);
            const numOfColors = panelColors.length;
            // 여기서 색 구간 나중에 정해주기
            const color = contribution.value >= numOfColors ? panelColors[numOfColors - 1] : panelColors[contribution.value];
            const dom = <rect key={"panel_key_" + i + "_" + j} x={pos.x} y={pos.y} width={panelSize} height={panelSize} fill={color} rx="3" />;

            innerDom.push(dom);
        }
    }

    for (let i = 0; i < weekNames.length; i++) {
        const textBasePos = getPanelPosition(0, i);
        const dom = (
            <text
                key={"week_key_" + i}
                style={{
                    fontSize: 12,
                    alignmentBaseline: "central",
                    fill: i === 0 ? "#E8828D" : i === 6 ? "#828CE8" : "#aaa"
                }}
                x={textBasePos.x - panelSize / 2 - 2}
                y={textBasePos.y + panelSize / 2}
                textAnchor={"middle"}
            >
                {weekNames[i]}
            </text>
        );

        innerDom.push(dom);
    }

    let prevMonth = -1;

    for (let i = 0; i < columns; i++) {
        const c = contributions[i][0];

        if (c === null) {
            continue;
        }

        if (columns > 1 && i === 0 && c.month !== contributions[i + 1][0]?.month) {
            continue;
        }

        if (c.month !== prevMonth) {
            const textBasePos = getPanelPosition(i, 0);
            innerDom.push(
                <text
                    key={"month_key_" + i}
                    style={{
                        fontSize: 12,
                        alignmentBaseline: "central",
                        fill: "#888",
                        marginRight: "3px"
                    }}
                    x={textBasePos.x + panelSize / 2}
                    y={textBasePos.y - panelSize / 2 - 2}
                    textAnchor={"middle"}
                >
                    {monthNames[c.month]}
                </text>
            );
        }

        prevMonth = c.month;
    }

    return (
        <Measure bounds onResize={(rect) => updateSize(rect.bounds)}>
            {({ measureRef }) => (
                <div ref={measureRef} style={{ width: "100%" }}>
                    <svg
                        style={{
                            fontFamily: "Helvetica, arial, nimbussansl, liberationsans, freesans, clean, sans-serif",
                            width: "100%"
                        }}
                        height="119"
                    >
                        {innerDom}
                    </svg>
                </div>
            )}
        </Measure>
    );
}
