import { useState } from "react";
import { useNavigate } from "react-router";

import * as s from "./style";

import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";

import DiffSelectSection from "components/section/diffselectsection/DiffSelectSection";
import ErrorModal from "components/modals/errormodal/ErrorModal";

const diffList = ["단어", "단락", "단순절"];

export default function ProblemPage() {
    const [diff, setDiff] = useState("");
    const [problem, setProblem] = useState({});
    const [update, setUpdate] = useState(true);

    const [error, setError] = useState(false);
    const navigate = useNavigate();

    return (
        <s.Main>
            <DiffSelectSection
                props={{ diff, diffList, setDiff, title: "발음 연습", description: "주어진 단어 혹은 문장을 읽고, 발음을 연습해보세요." }}
            />
            <>
                <ProblemSection props={{ diff, problem, setProblem, update, setUpdate, setError }} />
                <PronunciationSection props={{ problem, diff, setUpdate, setError }} />
            </>

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
