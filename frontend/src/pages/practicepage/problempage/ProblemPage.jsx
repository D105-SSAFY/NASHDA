import { useState } from "react";

import * as s from "./style";

import DiffSelectSection from "components/section/diffselectsection/DiffSelectSection";
import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";

const diffList = ["단어", "구", "절", "복합절"];

export default function ProblemPage() {
    const [diff, setDiff] = useState("");
    const [pronunciation, setPronunciation] = useState("");

    return (
        <s.Main>
            {diff ? (
                <>
                    <ProblemSection props={{ pronunciation, setPronunciation }} />
                    <PronunciationSection props={{ pronunciation }} />
                </>
            ) : (
                <DiffSelectSection props={{ diffList, setDiff, title: "발음 연습", description: "발음 연습의 난이도를 선택해주세요." }} />
            )}
        </s.Main>
    );
}
