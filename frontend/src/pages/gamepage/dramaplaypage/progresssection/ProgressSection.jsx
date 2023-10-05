import * as s from "./style";

export default function ProgressSection({ props: { total, now } }) {
    return (
        <s.Section>
            <header>
                <h2>전체 시간 표시 영역</h2>
            </header>
            <s.Progress progress={(now / total) * 100}></s.Progress>
        </s.Section>
    );
}
