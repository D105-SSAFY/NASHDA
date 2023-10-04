import * as s from "./style";

export default function DiffSelectSection({ props: { diff, diffList, setDiff, title, description } }) {
    return (
        <s.Wrapper showModal={Boolean(diff)}>
            <s.Section>
                <s.Header>
                    <h2>{title}</h2>
                    <p>{description}</p>
                </s.Header>
                <s.List>
                    {diffList.map((diff) => {
                        return (
                            <li key={diff}>
                                <s.Button onClick={() => setDiff(diff)}>
                                    <s.TextWrapper>
                                        {diff.split("").map((word, wordIdx) => {
                                            return <span key={diff + word + String(wordIdx)}>{word}</span>;
                                        })}
                                    </s.TextWrapper>
                                </s.Button>
                            </li>
                        );
                    })}
                </s.List>
            </s.Section>
        </s.Wrapper>
    );
}
