import * as s from "./style";

export default function BorderButton({ children, props: { color, callback } }) {
    return (
        <s.Button onClick={callback} color={color}>
            {children}
        </s.Button>
    );
}
