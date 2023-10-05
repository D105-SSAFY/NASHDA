import { useState, useEffect, useRef } from "react";
import { useSelector, useDispatch } from "react-redux";

import eetch from "apis/eetch";

import * as w from "./style";

import SelectInput from "components/input/FormSelectCol";

import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";
import TipsAndUpdatesIcon from "@mui/icons-material/TipsAndUpdates";
import GraphicEqIcon from "@mui/icons-material/GraphicEq";
import StopCircleIcon from "@mui/icons-material/StopCircle";
import CheckBoxIcon from "@mui/icons-material/CheckBox";

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
                newWeekExists.push({ hobbyIdx: idx + 1, hobby: obj.week + "주차 " + obj.try_count + "차 테스트" });
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

    const WeekBlankCard = ({ b }) => {
        const audioRef = useRef();
        const [isPlaying, setIsPlaying] = useState(false);
        const [showAnswer, setShowAnswer] = useState(false);

        const handleTogglePlay = () => {
            if (isPlaying) {
                audioRef.current.pause();
                audioRef.current.currentTime = 0;
            } else {
                audioRef.current.play();
            }

            setIsPlaying(!isPlaying);
        };

        useEffect(() => {
            const audioElement = audioRef.current;

            const handleEnded = () => setIsPlaying(false);

            if (audioElement) {
                audioElement.addEventListener("ended", handleEnded);
            }

            return () => {
                if (audioElement) {
                    audioElement.removeEventListener("ended", handleEnded);
                }
            };
        }, []);

        return (
            <w.weekBlankItem>
                <w.BlankImg src={b.imgURL} />
                <w.BlankAnswer>
                    &quot;
                    {b.word.reduce((acc, cur) => {
                        const regExp = new RegExp(cur, "g");
                        return acc.replace(regExp, "__".repeat(cur.length));
                    }, b.answer)}
                    &quot;
                </w.BlankAnswer>
                <w.BlankHint>
                    힌트
                    <TipsAndUpdatesIcon />
                </w.BlankHint>
                <w.HintWrapper>
                    {b.hint.map((text, idx) => (
                        <w.Hint key={idx}>
                            {idx + 1}. {text}
                        </w.Hint>
                    ))}
                </w.HintWrapper>
                <w.AudioWrapper isPlaying={isPlaying}>
                    <w.AudioAnswer ref={audioRef} src={b.soundUrl} style={{ visibility: "hidden" }} />

                    <button onClick={handleTogglePlay}>당신의 음성 듣기{isPlaying ? <StopCircleIcon /> : <GraphicEqIcon />}</button>
                </w.AudioWrapper>
                <w.AnswerWrapper>
                    <w.AnswerToggle onClick={() => setShowAnswer(!showAnswer)}> 정답 확인 </w.AnswerToggle>
                    <w.Answers showAnswer={showAnswer}>
                        {b.word.map((text, idx) => (
                            <w.Answer key={idx}>{text}</w.Answer>
                        ))}
                    </w.Answers>
                </w.AnswerWrapper>
            </w.weekBlankItem>
        );
    };

    const WeekSpeed1Card = ({ b }) => {
        const audioRef = useRef();
        const [isPlaying, setIsPlaying] = useState(false);

        const handleTogglePlay = () => {
            if (isPlaying) {
                audioRef.current.pause();
                audioRef.current.currentTime = 0;
            } else {
                audioRef.current.play();
            }

            setIsPlaying(!isPlaying);
        };

        useEffect(() => {
            const audioElement = audioRef.current;

            const handleEnded = () => setIsPlaying(false);

            if (audioElement) {
                audioElement.addEventListener("ended", handleEnded);
            }

            return () => {
                if (audioElement) {
                    audioElement.removeEventListener("ended", handleEnded);
                }
            };
        }, []);

        return (
            <w.WeekSpeedOneItem isPlaying={isPlaying}>
                <w.WeekSpeedOneImg src={b.img} />
                <w.WeekSpeedOneAnswerWrapper>
                    <w.WeekSpeedOneType>정답</w.WeekSpeedOneType>
                    <w.WeekSpeedOneAnswer>{b.answer}</w.WeekSpeedOneAnswer>
                    <w.WeekSpeedOneType>내 발음</w.WeekSpeedOneType>
                    <w.WeekSpeedOneSTT>{b.userSTT}</w.WeekSpeedOneSTT>
                </w.WeekSpeedOneAnswerWrapper>
                <w.AudioAnswer ref={audioRef} src={b.soundUrl} style={{ visibility: "hidden" }} />
                <button onClick={handleTogglePlay}>당신의 음성 듣기{isPlaying ? <StopCircleIcon /> : <GraphicEqIcon />}</button>
            </w.WeekSpeedOneItem>
        );
    };

    const WeekSpeed2Card = ({ b }) => {
        console.log(b);
        return (
            <w.WeekSpeedTwoItem>
                <w.WeekSpeedTwoQuizTitle>다음 중 &apos;{b.answer}&apos; 사진을 골라볼까요?</w.WeekSpeedTwoQuizTitle>
                <w.WeekSpeedTwoPhotoWrapper>
                    {b.img.map((img, idx) => {
                        return (
                            <w.WeekSpeedTwoImgWrapper key={idx}>
                                <w.WeekSpeedTwoImg answer={img === b.userAnswer} src={img} />
                                {img === b.userAnswer ? <CheckBoxIcon /> : null}
                            </w.WeekSpeedTwoImgWrapper>
                        );
                    })}
                </w.WeekSpeedTwoPhotoWrapper>
            </w.WeekSpeedTwoItem>
        );
    };

    const weekDetailsContent = () => {
        const content = [];
        if (weekDetails.length !== 0) {
            const blank = weekDetails[weekSelected - 1].blankTest;
            const speed1 = weekDetails[weekSelected - 1].speedTest1;
            const speed2 = weekDetails[weekSelected - 1].speedTest2;

            content.push(
                <w.weekDetailWrapper key={0}>
                    <w.WrapperTitle>빈칸 채우기</w.WrapperTitle>
                    <w.weekBlankWrapper>
                        {blank.map((b, idx) => (
                            <WeekBlankCard key={idx} b={b} />
                        ))}
                    </w.weekBlankWrapper>
                    <w.WrapperTitle>스피드 퀴즈</w.WrapperTitle>
                    <w.WeekSpeedOneWrapper>
                        {speed1.map((b, idx) => (
                            <WeekSpeed1Card key={idx} b={b} />
                        ))}
                    </w.WeekSpeedOneWrapper>
                    <w.WrapperTitle>객관식 그림 맞추기</w.WrapperTitle>
                    <w.WeekSpeedTwoWrapper>
                        {speed2.map((b, idx) => (
                            <WeekSpeed2Card key={idx} b={b} />
                        ))}
                    </w.WeekSpeedTwoWrapper>
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
                                Idx: weekSelected
                            }}
                        />
                        {weekDetails === 0 ? null : weekDetailsContent()}
                    </w.WeeksResultWrapper>
                </>
            )}
        </w.WeekWrapper>
    );
}
