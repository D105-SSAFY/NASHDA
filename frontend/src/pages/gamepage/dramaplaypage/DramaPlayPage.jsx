import { useEffect, useState } from "react";

import * as s from "./style";

import DiffSelectSection from "components/section/diffselectsection/DiffSelectSection";

import ProblemSection from "./problemsection/ProblemSection";
import PronunciationSection from "./pronunciationsection/PronunciationSection";

const diffList = ["상", "중", "하"];
// Const diff = diffList[Math.floor(Math.random() * diffList.length)];

export default function DramaPlayPage() {
    const [diff, setDiff] = useState("");
    const [problem, setProblem] = useState({});
    const [sentence, setSentence] = useState("");

    useEffect(() => {
        setProblem({
            img: "https://plus.unsplash.com/premium_photo-1661897401664-ca20addf3e8d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2069&q=80",
            word: ["곰", "강", "물고기"],
            hint: [
                ["명사", "깊은 산이나 북극 지방에 살며 나무에 잘 오르고 잡식성으로 대부분 겨울에는 굴속에서 겨울잠을 잔다."],
                ["명사", "깊은 산이나 북극 지방에 살며 나무에 잘 오르고 잡식성으로 대부분 겨울에는 굴속에서 겨울잠을 잔다."],
                ["명사", "깊은 산이나 북극 지방에 살며 나무에 잘 오르고 잡식성으로 대부분 겨울에는 굴속에서 겨울잠을 잔다."]
            ],
            answer: "곰이 강에서 물고기를 잡고 있습니다."
        });
    }, []);

    useEffect(() => {
        if (!problem.answer) {
            return;
        }

        let sentence = problem.answer;

        if (diff === "하") {
            sentence = sentence.replace(problem.word[Math.floor(Math.random() * problem.word.length)], "__");
        } else if (diff === "중") {
            const execption = problem.word[Math.floor(Math.random() * problem.word.length)];

            problem.word.forEach((word) => {
                if (execption === word) {
                    return;
                }

                sentence = sentence.replace(word, "__");
            });
        } else {
            problem.word.forEach((word) => {
                sentence = sentence.replace(word, "__");
            });
        }

        setSentence(sentence);
    }, [problem, diff]);

    return (
        <s.Main>
            {diff ? (
                <>
                    <ProblemSection props={{ problem, sentence }} />
                    <PronunciationSection props={{ problem, sentence }} />
                </>
            ) : (
                <DiffSelectSection props={{ diffList, setDiff, title: "드라마 플레이", description: "그림을 보고 문장을 채워주세요." }} />
            )}
        </s.Main>
    );
}
