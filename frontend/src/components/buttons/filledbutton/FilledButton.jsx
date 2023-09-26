import * as s from "./style";

export default function FilledButton({ children, props: { background, color, callback } }) {
    return (
        <s.Button onClick={callback} background={background} color={color}>
            {children}
        </s.Button>
    );
}
