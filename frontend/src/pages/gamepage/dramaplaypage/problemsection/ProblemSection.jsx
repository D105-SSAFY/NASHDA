import { useCallback, useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import * as s from "./style";

import eetch from "apis/eetch";

export default function ProblemSection({
    props: {
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
    }
}) {
    const [problemTime, setProblemtime] = useState(30);
    const problemTimer = useRef(null);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();

    const startProblemTimer = () => {
        if (problemTimer.current) {
            clearTimeout(problemTimer.current);
        }

        problemTimer.current = setInterval(() => {
            setProblemtime((time) => time - 1);
        }, 1000);
    };

    const stopProblemTimer = () => {
        if (problemTimer.current) {
            clearTimeout(problemTimer.current);
        }
    };

    const endProblemTimer = () => {
        if (problemTimer.current) {
            clearTimeout(problemTimer.current);
        }

        problemTimer.current = null;
        setProblemtime(30);
    };

    useEffect(() => {
        if (problemIndex === -1 || problemIndex === 4) {
            endProblemTimer();

            return;
        }

        endProblemTimer();
        startProblemTimer();
    }, [problemIndex]);

    useEffect(() => {
        if (problemTime === 10) {
            setShowHint(true);
        }

        if (problemTime === -1) {
            setProblemIndex((index) => index + 1);
        }
    }, [problemTime]);

    const getProblem = useCallback(async () => {
        const values = {};

        values.user = user;

        eetch
            .tokenValidation(eetch.blank, values, dispatch)
            .then((result) => {
                setProblemList(result.data);
            })
            .catch(() => {
                setError(true);
            });
    }, []);

    useEffect(() => {
        if (!diff) {
            return;
        }

        if (problemIndex !== -1) {
            return;
        }

        getProblem();
    }, [diff, problemIndex]);

    useEffect(() => {
        if (problemList.length === 0) {
            return;
        }

        setProblemIndex(0);
    }, [problemList]);

    useEffect(() => {
        if (problemIndex === -1) {
            return;
        }

        if (!problemList[problemIndex].answer) {
            return;
        }

        let sentence = problemList[problemIndex].answer.replaceAll(" ", "  ");
        const hint = [];

        if (diff === "쉬움") {
            const index = Math.floor(Math.random() * problemList[problemIndex].word.length);

            sentence = sentence.replace(problemList[problemIndex].word[index], "__ ".repeat(problemList[problemIndex].word[index].length));
            hint.push(problemList[problemIndex].hint[index]);
        } else if (diff === "중간") {
            const exec = Math.floor(Math.random() * problemList[problemIndex].word.length);

            problemList[problemIndex].word.forEach((word, index) => {
                if (index === exec) {
                    return;
                }

                sentence = sentence.replace(word, "__ ".repeat(word.length));
            });

            problemList[problemIndex].hint.forEach((nowHint, index) => {
                if (index === exec) {
                    return;
                }

                hint.push(nowHint);
            });
        } else {
            problemList[problemIndex].word.forEach((word) => {
                sentence = sentence.replace(word, "__ ".repeat(word.length));
            });

            problemList[problemIndex].hint.forEach((nowHint) => {
                hint.push(nowHint);
            });
        }

        setSentence(sentence);
        setHint(hint);
    }, [problemIndex, diff]);

    useEffect(() => {
        if (onHintModal) {
            stopProblemTimer();
            stopTotalTimer();

            return;
        }

        if (!onHintModal && problemTime !== 30) {
            startProblemTimer();
            startTotalTimer();
        }
    }, [onHintModal]);

    return (
        <s.Section>
            <s.Header>
                <h2>문제 영역</h2>
            </s.Header>
            {sentence ? <img src={problemList[problemIndex].imgURL} alt="" /> : <div style={{ height: "260px" }}></div>}
            <s.Text>올바른 문장</s.Text>
            <s.Sentence>{sentence}</s.Sentence>
            <s.Time alret={problemTime <= 10}>{problemTime}</s.Time>
        </s.Section>
    );
}
