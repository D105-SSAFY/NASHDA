import * as s from "./style";

export default function ProblemSection({ props: { problem } }) {
    const getSentence = () => {
        let sentence = problem.answer;

        problem.word.forEach((word) => {
            sentence = sentence.replace(word, "__");
        });

        return sentence;
    };

    return (
        <s.Section>
            <s.Header>
                <h2>문제 영역</h2>
            </s.Header>
            <img src={problem.imgURL} alt="" />
            <s.Text>올바른 문장</s.Text>
            <s.Sentence>{getSentence()}</s.Sentence>
        </s.Section>
    );
}
