import * as s from "./style";

export default function FilledButton({ children, props: { background, color, hovercolor, callback } }) {
    return (
        <s.Button onClick={callback} background={background} color={color} hovercolor={hovercolor}>
            {children}
        </s.Button>
    );
}
