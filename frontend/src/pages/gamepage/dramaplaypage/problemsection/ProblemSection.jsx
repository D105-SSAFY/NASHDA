import * as s from "./style";

export default function ProblemSection({ props: { problem, sentence } }) {
    if (!sentence) {
        return;
    }

    return (
        <s.Section>
            <s.Header>
                <h2>문제 영역</h2>
            </s.Header>
            <img src={problem.img} alt="" />
            <p>{sentence}</p>
        </s.Section>
    );
}
