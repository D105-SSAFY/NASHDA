import { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";

import eetch from "apis/eetch";

import * as g from "./style";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Games({ isGame, setIsGame }) {
    const dispatch = useDispatch();
    const user = useSelector((state) => state.user);
    const [games, setGames] = useState([]);
    const [blankGames, setBlankGames] = useState([]);
    const [speedGames, setSpeedGames] = useState([]);

    useEffect(() => {
        eetch.tokenValidation(eetch.games, { user }, dispatch).then((res) => {
            setGames(res.data);
        });
    }, []);

    useEffect(() => {
        if (games.length !== 0) {
            setIsGame(true);
        }

        games.forEach((obj) => {
            eetch.tokenValidation(eetch.gameBlankWeek, { user, week: obj.week }, dispatch).then((res) => {
                const temp = res.data;
                temp.week = obj.week;
                setBlankGames((prev) => [...prev, temp]);
            });

            eetch.tokenValidation(eetch.gameSpeedWeek, { user, week: obj.week }, dispatch).then((res) => {
                const temp = res.data;
                temp.week = obj.week;
                setSpeedGames((prev) => [...prev, temp]);
            });
        });
    }, [games]);

    const gamesRecentContent = () => {
        const gameList = [];
        if (blankGames.length !== 0 && speedGames.length !== 0) {
            gameList.push(
                <tr key={0}>
                    <th>{blankGames[0].week}</th>
                    <td>
                        {blankGames[0].start_date} ~ {blankGames[0].end_date}
                    </td>
                    <td>
                        {blankGames[0].score} / {blankGames[0].total}
                    </td>
                    <td>{blankGames[0].set}</td>
                    <td>
                        {speedGames[0].score} / {speedGames[0].total}
                    </td>
                    <td>{speedGames[0].set}</td>
                </tr>
            );
        }

        return gameList;
    };

    const gamesBlankContentDetail = () => {
        const gameList = [];
        blankGames.forEach((obj, index) => {
            gameList.push(
                <tr key={index}>
                    <th>{obj.week}</th>
                    <td>
                        {obj.start_date} ~ {obj.end_date}
                    </td>
                    <td>
                        {obj.score} / {obj.total}
                    </td>
                    <td>{obj.set}</td>
                </tr>
            );
        });

        return gameList;
    };

    const gamesSpeedContentDetail = () => {
        const gameList = [];
        speedGames.forEach((obj, index) => {
            gameList.push(
                <tr key={index}>
                    <th>{obj.week}</th>
                    <td>
                        {obj.start_date} ~ {obj.end_date}
                    </td>
                    <td>
                        {obj.score} / {obj.total}
                    </td>
                    <td>{obj.set}</td>
                </tr>
            );
        });

        return gameList;
    };

    return (
        <g.GamesWrapper isGame={isGame}>
            {games.length === 0 ? (
                <g.NoDataWrapper>
                    <g.NoData>게임 플레이 기록이 없어요.</g.NoData>
                    <g.NoDataLink>
                        즐겁게 연습하기
                        <ArrowCircleRightOutlinedIcon />
                    </g.NoDataLink>
                </g.NoDataWrapper>
            ) : (
                <>
                    <g.TableTitle>최근</g.TableTitle>
                    <g.StatTable>
                        <g.StatTableHead>
                            <tr>
                                <th>주차</th>
                                <th>기간</th>
                                <th>공백 점수</th>
                                <th>공백 플레이 횟수</th>
                                <th>스피드 점수</th>
                                <th>스피드 플레이 횟수</th>
                            </tr>
                        </g.StatTableHead>
                        <g.StatTableBody>{gamesRecentContent()}</g.StatTableBody>
                    </g.StatTable>
                    <g.TableTitle>공백</g.TableTitle>
                    <g.StatTable>
                        <g.StatTableHead>
                            <tr>
                                <th>주차</th>
                                <th>기간</th>
                                <th>점수</th>
                                <th>플레이 횟수</th>
                            </tr>
                        </g.StatTableHead>
                        <g.StatTableBody>{gamesBlankContentDetail()}</g.StatTableBody>
                    </g.StatTable>
                    <g.TableTitle>스피드</g.TableTitle>
                    <g.StatTable>
                        <g.StatTableHead>
                            <tr>
                                <th>주차</th>
                                <th>기간</th>
                                <th>점수</th>
                                <th>플레이 횟수</th>
                            </tr>
                        </g.StatTableHead>
                        <g.StatTableBody>{gamesSpeedContentDetail()}</g.StatTableBody>
                    </g.StatTable>
                </>
            )}
        </g.GamesWrapper>
    );
}
