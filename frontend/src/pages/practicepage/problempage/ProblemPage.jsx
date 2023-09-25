import * as s from "./style";

import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";
import { useState } from "react";

export default function ProblemPage() {
    const [pronunciation, setPronunciation] = useState("");

    return (
        <s.Main>
            <ProblemSection props={{ pronunciation, setPronunciation }} />
            <PronunciationSection props={{ pronunciation }} />
        </s.Main>
    );
}
