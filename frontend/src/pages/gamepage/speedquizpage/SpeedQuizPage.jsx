import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import DiffSelectSection from "components/section/diffselectsection/DiffSelectSection";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";
import ErrorModal from "components/modals/errormodal/ErrorModal";

import ProgressSection from "./progresssection/ProgressSection";
import SetWordSection from "./setwordsection/SetWordSection";
import SetImageSection from "./setimagesection/SetImageSection";

import RestartAltIcon from "@mui/icons-material/RestartAlt";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

import eetch from "apis/eetch";

const diffList = ["쉬움", "중간", "어려움"];

export default function SpeedQuizPage() {
    const [diff, setDiff] = useState("");
    const [numProblem, setNumProblem] = useState(0);
    const [problem, setProblem] = useState({});
    const passedProblemList = useRef([]);
    const [end, setEnd] = useState(false);
    const [correct, setCorrect] = useState(0);

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
        if (!diff) {
            return;
        }

        startTotalTimer();
    }, [diff]);

    useEffect(() => {
        if (totalTime !== -1) {
            return;
        }

        endTotalTimer();
        setEnd(true);
    }, [totalTime]);

    useEffect(() => {
        const values = {};

        values.user = user;

        eetch
            .tokenValidation(eetch.speed, values, dispatch)
            .then((result) => {
                setNumProblem(result.data);
            })
            .catch(() => {
                setError(true);
            });
    }, []);

    const getNextProblem = () => {
        if (numProblem === passedProblemList.current.length) {
            setEnd(true);

            return;
        }

        let index = 0;

        while (index === 0 || passedProblemList.current.includes(index)) {
            index = Math.floor(Math.random() * numProblem) + 1;
        }

        const values = {};

        values.user = user;
        values.index = index;

        let callback = () => {};

        if (diff === "쉬움") {
            callback = eetch.speedOne;
        } else if (diff === "중간") {
            callback = eetch.speedTwo;
        } else {
            callback = Math.floor(Math.random() * 2) ? eetch.speedOne : eetch.speedTwo;
        }

        eetch
            .tokenValidation(callback, values, dispatch)
            .then((result) => {
                passedProblemList.current.push(index);
                setProblem({ ...result.data });
            })
            .catch(() => {
                setError(true);
            });
    };

    useEffect(() => {
        if (!diff || numProblem === 0) {
            return;
        }

        getNextProblem();
    }, [diff, numProblem]);

    useEffect(() => {
        if (!end) {
            return;
        }

        endTotalTimer();

        const values = {};

        values.user = user;
        values.total = passedProblemList.current.length;
        values.score = correct;

        eetch
            .tokenValidation(eetch.speedResult, values, dispatch)
            .then(() => {
                // console.log(result);
            })
            .catch(() => {
                setError(true);
            });
    }, [end]);

    return (
        <s.Main>
            {end ? (
                <s.Section>
                    <s.Header>
                        <h2>테스트 종료</h2>
                        <p>
                            {passedProblemList.current.length} 문제 중 {correct} 문제 정답
                        </p>
                    </s.Header>
                    <s.ButtonWrapper>
                        <FilledButton
                            props={{
                                background: "rgba(68, 71, 90, 0.7)",
                                color: "#ffffff",
                                hovercolor: "#44475A",
                                callback() {
                                    window.location.reload();
                                }
                            }}
                        >
                            <RestartAltIcon />
                            <span>다시 하기</span>
                        </FilledButton>
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
                    <DiffSelectSection
                        props={{ diff, diffList, setDiff, title: "스피드 퀴즈", description: "제한 시간 안에 최대한 많은 문제를 맞춰주세요." }}
                    />
                    <ProgressSection props={{ total: 120, now: totalTime }} />
                    {problem.type === 1 ? (
                        <SetWordSection props={{ problem, getNextProblem, setCorrect, setError }} />
                    ) : (
                        <SetImageSection props={{ problem, getNextProblem, setCorrect }} />
                    )}
                </>
            )}

            <ErrorModal
                props={{
                    title: "에러 발생",
                    content: "서버에 에러가 발생했습니다. 잠시 후 다시 시도해주세요.",
                    display: error,
                    callback() {
                        navigate("/main");
                    }
                }}
            />
        </s.Main>
    );
}
