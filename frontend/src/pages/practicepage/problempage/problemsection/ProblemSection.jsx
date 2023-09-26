import { useEffect, useState } from "react";

import * as s from "./style";

import VolumeUpIcon from "@mui/icons-material/VolumeUp";

export default function ProblemSection({ props: { pronunciation, setPronunciation } }) {
    const [problem, setProblem] = useState("");

    useEffect(() => {
        setProblem("허락해주면 안 될까");
        setPronunciation("허라캐주면 안 될까");
    }, []);

    return (
        <s.Section>
            <s.Header>
                <h2>문제 영역</h2>
            </s.Header>
            <s.Problem>{problem}</s.Problem>
            <s.RightPron>올바른 발음</s.RightPron>
            <s.SpeakWrapper>
                <s.Pron>&quot;{pronunciation}&quot;</s.Pron>
                <s.SpeakButton>
                    <VolumeUpIcon />
                </s.SpeakButton>
            </s.SpeakWrapper>
        </s.Section>
    );
}
