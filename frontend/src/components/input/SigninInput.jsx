import * as Styled from "./style";

export default function SigninInput({ data: { text, type, id, name, onChangeFunc, placeholder, value, readOnly, ref, check } }) {
    // eslint-disable-next-line no-return-assign
    return (
        <Styled.StyledDiv>
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
            <Styled.StyledButton check={(check ??= false)}>인증</Styled.StyledButton>
        </Styled.StyledDiv>
    );
}
