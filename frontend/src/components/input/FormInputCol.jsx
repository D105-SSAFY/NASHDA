import * as f from "./style";

export default function FormInputCol({ data: { text, type, id, name, onChangeFunc, onClickFunc, placeholder, value, readOnly, ref, check } }) {
    // eslint-disable-next-line no-return-assign
    return (
        <f.StyledDiv>
            <f.StyledLabel htmlFor={id}>{text}</f.StyledLabel>
            <f.StyledInput
                type={type}
                id={id}
                name={name}
                onChange={onChangeFunc}
                ref={ref}
                placeholder={(placeholder ??= "")}
                value={value}
                readOnly={(readOnly ??= false)}
                check={(check ??= false)}
            ></f.StyledInput>
            <f.StyledButton onClick={onClickFunc} check={(check ??= false)}>
                인증
            </f.StyledButton>
        </f.StyledDiv>
    );
}
