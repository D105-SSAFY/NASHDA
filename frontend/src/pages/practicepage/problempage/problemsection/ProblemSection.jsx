import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import * as s from "./style";

import eetch from "apis/eetch";

const diffWord = {
    단어: "word",
    단락: "phase",
    단순절: "simple",
    복합절: "complex"
};

export default function ProblemSection({ props: { diff, problem, setProblem, update, setUpdate, setError } }) {
    const [numProblem, setNumProblem] = useState(0);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();

    useEffect(() => {
        if (!diff) {
            return;
        }

        const values = {};

        values.type = diffWord[diff];
        values.user = user;

        eetch
            .tokenValidation(eetch.problemNum, values, dispatch)
            .then((result) => {
                setNumProblem(result.data);
            })
            .catch(() => {
                setError(true);
            });
    }, [diff]);

    useEffect(() => {
        if (numProblem === 0 || !update) {
            return;
        }

        const number = Math.floor(Math.random() * numProblem) + 1;
        const values = {};

        values.type = diffWord[diff];
        values.number = number;
        values.user = user;

        eetch
            .tokenValidation(eetch.getProblem, values, dispatch)
            .then((result) => {
                setProblem(result.data);
                setUpdate(false);
            })
            .catch(() => {
                setError(true);
            });
    }, [update, numProblem]);

    return (
        <s.Section>
            <s.Header>
                <h2>문제 영역</h2>
            </s.Header>
            <s.Problem>{problem.origin}</s.Problem>
            <s.RightPron>올바른 발음</s.RightPron>
            <s.SpeakWrapper>
                <s.Pron>&quot;{problem.convert}&quot;</s.Pron>
            </s.SpeakWrapper>
        </s.Section>
    );
}
