import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";

export default function DiffSelectSection({ props: { diffList, setDiff, title, description } }) {
    return (
        <s.Section>
            <s.Header>
                <h2>{title}</h2>
                <p>{description}</p>
            </s.Header>
            <s.List>
                {diffList.map((diff) => {
                    return (
                        <li key={diff}>
                            <FilledButton
                                props={{
                                    background: "rgba(68, 71, 90, 0.7)",
                                    color: "#ffffff",
                                    hovercolor: "#44475A",
                                    callback: () => setDiff(diff)
                                }}
                            >
                                <span>{diff}</span>
                            </FilledButton>
                        </li>
                    );
                })}
            </s.List>
        </s.Section>
    );
}
