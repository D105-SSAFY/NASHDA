import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";
import ProgressSection from "./progresssection/ProgressSection";

import DiffSelectSection from "components/section/diffselectsection/DiffSelectSection";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";
import ErrorModal from "components/modals/errormodal/ErrorModal";

import RedoIcon from "@mui/icons-material/Redo";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

import eetch from "apis/eetch";

const diffList = ["쉬움", "중간", "어려움"];

export default function DramaPlayPage() {
    const [diff, setDiff] = useState("");
    const [problemList, setProblemList] = useState([]);
    const [problemIndex, setProblemIndex] = useState(-1);
    const [sentence, setSentence] = useState("");
    const [correct, setCorrect] = useState(0);
    const [showHint, setShowHint] = useState(false);
    const [hint, setHint] = useState([]);
    const [onHintModal, setOnHintModal] = useState(false);

    const [error, setError] = useState(false);

    const [totalTime, setTotaltime] = useState(200);
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

    const stopTotalTimer = () => {
        if (totalTimer.current) {
            clearTimeout(totalTimer.current);
        }
    };

    const endTotalTimer = () => {
        if (totalTimer.current) {
            clearTimeout(totalTimer.current);
        }

        totalTimer.current = null;
        setProblemIndex(4);
        setTotaltime(200);
    };

    useEffect(() => {
        if (totalTime !== -1) {
            return;
        }

        endTotalTimer();
        setProblemIndex(4);
    }, [totalTime]);

    useEffect(() => {
        if (problemIndex !== 0) {
            return;
        }

        startTotalTimer();
    }, [problemIndex]);

    useEffect(() => {
        setShowHint(false);
    }, [problemIndex]);

    useEffect(() => {
        if (problemIndex !== 4) {
            return;
        }

        endTotalTimer();

        const values = {};

        values.user = user;
        values.total = 4;
        values.score = correct;
        values.level = diff === "상" ? 3 : diff === "중" ? 2 : 1;

        eetch
            .tokenValidation(eetch.blankResult, values, dispatch)
            .then(() => {
                // console.log(result);
            })
            .catch(() => {
                navigate("/signin");
            });
    }, [problemIndex]);

    return (
        <s.Main>
            <DiffSelectSection props={{ diff, diffList, setDiff, title: "드라마 플레이", description: "그림을 보고 문장의 빈칸을 채워주세요." }} />
            {problemIndex === 4 ? (
                <s.Section>
                    <s.Header>
                        <h2>테스트 종료</h2>
                        <s.Content>4 문제 중 {correct} 문제 정답</s.Content>
                    </s.Header>
                    <s.ButtonWrapper>
                        <FilledButton
                            props={{
                                background: "rgba(68, 71, 90, 0.7)",
                                color: "#ffffff",
                                hovercolor: "#44475A",
                                callback() {
                                    setProblemList([]);
                                    setProblemIndex(-1);
                                    setSentence("");
                                    setCorrect(0);
                                }
                            }}
                        >
                            <RedoIcon />
                            <span>계속 풀기</span>
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
                    <ProgressSection props={{ total: 200, now: totalTime }} />
                    <ProblemSection
                        props={{
                            diff,
                            problemList,
                            setProblemList,
                            problemIndex,
                            setProblemIndex,
                            sentence,
                            setSentence,
                            setHint,
                            setShowHint,
                            onHintModal,
                            startTotalTimer,
                            stopTotalTimer,
                            setError
                        }}
                    />
                    <PronunciationSection
                        props={{
                            problemList,
                            problemIndex,
                            sentence,
                            setProblemIndex,
                            setCorrect,
                            hint,
                            showHint,
                            onHintModal,
                            setOnHintModal,
                            setError
                        }}
                    />
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
