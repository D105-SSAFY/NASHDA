import * as s from "./style";

export default function SummarySection() {
    return (
        <s.Section>
            <s.Header>
                <h2>다시 보아 너무 반갑네요!</h2>
                <p>
                    <span>@consolas</span> 님의 연속격파 <span>12일차</span>, 더 달려볼까요?
                </p>
            </s.Header>
            <s.TextWrapper>
                <p>
                    지금까지 <s.Words>단어 12,854개</s.Words> / <s.Sentences>문장 2,480개</s.Sentences> / <s.Chats>대화 711번</s.Chats> 완료
                </p>
                <p>* 아직 주간 시험에 응시하지 않으셨어요.</p>
            </s.TextWrapper>
        </s.Section>
    );
}
