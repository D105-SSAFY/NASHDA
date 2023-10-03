import { useState } from "react";

import * as s from "./style";

import DiffSelectSection from "components/section/diffselectsection/DiffSelectSection";
import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";

const diffList = ["단어", "구", "절", "복합절"];

export default function ProblemPage() {
    const [diff, setDiff] = useState("");
    const [problem, setProblem] = useState({});
    const [update, setUpdate] = useState(true);

    return (
        <s.Main>
            {diff ? (
                <>
                    <ProblemSection props={{ diff, problem, setProblem, update, setUpdate }} />
                    <PronunciationSection props={{ problem, diff, setUpdate }} />
                </>
            ) : (
                <DiffSelectSection props={{ diffList, setDiff, title: "난이도 선택", description: "발음 연습의 난이도를 선택해주세요." }} />
            )}
        </s.Main>
    );
}
