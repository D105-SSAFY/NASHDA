import * as s from "./style";

import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";

export default function DramaPlaySection({ props: { problem, getNextProblem, setCorrect, testIndex, problemIndex, setError } }) {
    return (
        <s.Section>
            <header>
                <h2>드라마 플레이 영역</h2>
            </header>
            <ProblemSection props={{ problem }} />
            <PronunciationSection props={{ problem, getNextProblem, setCorrect, testIndex, problemIndex, setError }} />
        </s.Section>
    );
}
