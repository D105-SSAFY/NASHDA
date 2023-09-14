import * as Styled from "./style";

export default function SigninInput({
    data: {
        text,
        type,
        id,
        name,
        onChangeFunc,
        placeholder,
        value,
        readOnly,
        ref,
    },
}) {
    return (
        <div style={{ width: "30%", position: "relative", margin: "10px" }}>
            <Styled.StyledLabel htmlFor={id}>{text}</Styled.StyledLabel>
            <Styled.StyledInput
                type={type}
                id={id}
                name={name}
                onChange={onChangeFunc}
                ref={ref}
                placeholder={(placeholder ??= "")}
                value={value}
                readOnly={(readOnly ??= false)}
            ></Styled.StyledInput>
        </div>
    );
}
