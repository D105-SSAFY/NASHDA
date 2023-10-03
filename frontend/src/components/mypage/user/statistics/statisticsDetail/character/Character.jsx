import { useState, useEffect } from "react";
import { useSelector } from "react-redux";

import eetch from "apis/eetch";

import GraphDivs from "components/common/graph/GraphDivs";

import * as c from "./style";
import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Character({ setDatas }) {
    const user = useSelector((state) => state.user);
    const [characters, setCharacters] = useState([]);
    const [charsInfo, setCharsInfo] = useState([]);

    useEffect(() => {
        eetch.practiceWord({ user }).then((res) => {
            const sorted = res.data.sort((a, b) => {
                return b.incorrect / b.total - a.incorrect / a.total;
            });
            const temp = [];
            let totalPercent = 0;

            sorted.slice(0, 9).forEach((obj) => {
                totalPercent += Math.floor((obj.incorrect / obj.total) * 100);
            });

            sorted.slice(0, 9).forEach((obj) => {
                temp.push({
                    id: "[" + obj.letter + "]" + obj.type.substring(0, 1),
                    label: obj.letter,
                    value: Math.floor((((obj.incorrect / obj.total) * 100) / totalPercent) * 100)
                });
            });

            setCharsInfo(sorted);
            setCharacters(temp);
        });
    }, []);

    useEffect(() => {
        if (characters.length !== 0) {
            setDatas(0, true);
        }
    }, [characters]);

    const charsContent = () => {
        const cont = [];
        cont.push(<c.CharactersLi key={0}>많이 틀린 다섯 가지</c.CharactersLi>);
        charsInfo.slice(0, 5).forEach((obj, index) => {
            cont.push(
                <c.CharactersLi key={index + 1}>
                    {obj.letter} [{obj.type}]
                    <c.CharactersInfo>
                        ( 전체 {obj.total}회 중 {obj.incorrect}번 틀림, {Math.floor((obj.incorrect / obj.total) * 100)}% )
                    </c.CharactersInfo>
                </c.CharactersLi>
            );
        });

        return cont;
    };

    const charsContentDetail = () => {
        const cont = [];
        charsInfo.forEach((obj, index) => {
            cont.push(
                <tr key={index}>
                    <th>{index + 1}</th>
                    <td>{obj.letter}</td>
                    <td>{obj.type}</td>
                    <td>{obj.incorrect}</td>
                    <td>{obj.total}</td>
                    <td>{Math.floor((obj.incorrect / obj.total) * 100)}%</td>
                </tr>
            );
        });

        return cont;
    };

    return (
        <>
            {characters.length === 0 ? (
                <c.NoDataWrapper>
                    <c.NoData>아직 문자 정확도에 대한 자료가 부족해요.</c.NoData>
                    <c.NoDataLink>
                        분석 시작
                        <ArrowCircleRightOutlinedIcon />
                    </c.NoDataLink>
                </c.NoDataWrapper>
            ) : (
                <>
                    <c.CharactersExp>
                        <c.GraphTarget>
                            <GraphDivs data={characters} />
                        </c.GraphTarget>
                        <c.CharactersUl>{charsContent()}</c.CharactersUl>
                    </c.CharactersExp>
                    <c.StatTable>
                        <c.StatTableHead>
                            <tr>
                                <th>순위</th>
                                <th>문자</th>
                                <th>위치</th>
                                <th>틀림</th>
                                <th>전체</th>
                                <th>틀린 비율</th>
                            </tr>
                        </c.StatTableHead>
                        <c.StatTableBody>{charsContentDetail()}</c.StatTableBody>
                    </c.StatTable>
                </>
            )}
        </>
    );
}
