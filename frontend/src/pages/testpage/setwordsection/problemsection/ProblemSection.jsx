import * as s from "./style";

export default function ProblemSection({ props: { problem } }) {
    return (
        <s.Section>
            <header>
                <h3>문제 영역</h3>
            </header>
            <s.Title>단어 맞추기 </s.Title>
            <s.Explain>다음 그림을 보고 적절한 단어를 말해보세요.</s.Explain>
            <s.ImageWrapper>
                <img src={problem.img} alt="" />
            </s.ImageWrapper>
        </s.Section>
    );
}
