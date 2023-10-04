import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import eetch from "apis/eetch";

import DramaPlaySection from "./dramaplaysection/DramaPlaySection";
import SetImageSection from "./setimagesection/SetImageSection";
import SetWordSection from "./setwordsection/SetWordSection";
import ProgressSection from "./progresssection/ProgressSection";

import BorderButton from "components/buttons/borderbutton/BorderButton";
import ErrorModal from "components/modals/errormodal/ErrorModal";

import StartIcon from "@mui/icons-material/Start";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

export default function TestPage() {
    const [problemList, setProblemList] = useState([]);
    const [testIndex, setTestIndex] = useState("");
    const [problemIndex, setProblemIndex] = useState(0);
    const [correct, setCorrect] = useState(0);
    const [start, setStart] = useState(false);
    const [over, setOver] = useState(false);
    const [error, setError] = useState(false);

    const [totalTime, setTotaltime] = useState(120);
    const totalTimer = useRef(null);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const startTotalTimer = () => {
        if (totalTimer.current) {
            clearTimeout(totalTimer.current);
        }

        totalTimer.current = setInterval(() => {
            setTotaltime((time) => time - 1);
        }, 1000);
    };

    const endTotalTimer = () => {
        if (totalTimer.current) {
            clearTimeout(totalTimer.current);
        }

        totalTimer.current = null;
        setTotaltime(120);
    };

    useEffect(() => {
        if (!start) {
            return;
        }

        startTotalTimer();
    }, [start]);

    useEffect(() => {
        if (totalTime !== -1) {
            return;
        }

        endTotalTimer();
        setProblemIndex(10);
    }, [totalTime]);

    useEffect(() => {
        if (!start) {
            return;
        }

        const values = {};

        values.user = user;

        eetch
            .tokenValidation(eetch.weekList, values, dispatch)
            .then((result) => {
                setTestIndex(result.data.index);

                const list = [];

                result.data.blank.forEach((blank) => {
                    list.push({
                        ...blank,
                        type: "blank"
                    });
                });

                result.data.speed1.forEach((speed1) => {
                    list.push({
                        ...speed1,
                        type: "speed1"
                    });
                });

                result.data.speed2.forEach((speed2) => {
                    list.push({
                        ...speed2,
                        type: "speed2"
                    });
                });

                setProblemList(list);
            })
            .catch(() => {
                setOver(true);
            });
    }, [start]);

    useEffect(() => {
        if (problemIndex < 10) {
            return;
        }

        endTotalTimer();

        const values = {};

        values.user = user;
        values.index = testIndex;
        values.score = correct;

        eetch
            .tokenValidation(eetch.weekResult, values, dispatch)
            .then(() => {})
            .catch(() => {
                setError(true);
            });
    }, [problemIndex]);

    const getNextProblem = () => {
        setProblemIndex((index) => index + 1);
    };

    return (
        <s.Main>
            {start ? (
                problemIndex === 10 ? (
                    <s.Section>
                        <s.Header>
                            <h2>테스트 종료</h2>
                            <s.Correct>10 문제 중 {correct} 문제 정답</s.Correct>
                        </s.Header>
                        <s.ButtonWrapper>
                            <BorderButton
                                props={{
                                    color: "rgba(68, 71, 90, 0.7)",
                                    callback() {
                                        navigate("/main");
                                    }
                                }}
                            >
                                <ExitToAppIcon />
                                <span>종료</span>
                            </BorderButton>
                        </s.ButtonWrapper>
                    </s.Section>
                ) : (
                    <>
                        <ProgressSection props={{ total: 120, now: totalTime }} />
                        {problemList.length !== 0 && problemList[problemIndex].type === "blank" ? (
                            <DramaPlaySection
                                props={{ problem: problemList[problemIndex], getNextProblem, setCorrect, testIndex, problemIndex, setError }}
                            />
                        ) : (
                            <></>
                        )}
                        {problemList.length !== 0 && problemList[problemIndex].type === "speed1" ? (
                            <SetWordSection
                                props={{ problem: problemList[problemIndex], getNextProblem, setCorrect, testIndex, problemIndex, setError }}
                            />
                        ) : (
                            <></>
                        )}
                        {problemList.length !== 0 && problemList[problemIndex].type === "speed2" ? (
                            <SetImageSection
                                props={{ problem: problemList[problemIndex], getNextProblem, setCorrect, testIndex, problemIndex, setError }}
                            />
                        ) : (
                            <></>
                        )}
                    </>
                )
            ) : (
                <s.Section>
                    <s.Header>
                        <h2>테스트</h2>
                        <s.Title>주간 테스트</s.Title>
                        <s.Content>주간 테스트를 완료하고 실력을 확인해보세요.</s.Content>
                    </s.Header>
                    <s.ButtonWrapper>
                        <BorderButton
                            props={{
                                color: "rgba(68, 71, 90, 0.7)",
                                callback() {
                                    setStart(true);
                                }
                            }}
                        >
                            <StartIcon />
                            <span>시작하기</span>
                        </BorderButton>
                    </s.ButtonWrapper>
                </s.Section>
            )}
            <ErrorModal
                props={{
                    title: (error && "에러 발생") || (over && "응시 횟수 초과"),
                    content:
                        (error && "서버에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.") ||
                        (over && "테스트는 주마다 3회 이하로 응시 할 수 있습니다."),
                    display: error || over,
                    callback() {
                        navigate("/main");
                    }
                }}
            />
        </s.Main>
    );
}
