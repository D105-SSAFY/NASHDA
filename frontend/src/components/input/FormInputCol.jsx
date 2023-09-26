import * as f from "./style";

export default function FormInputCol({ data: { text, type, id, name, onChangeFunc, onClickFunc, placeholder, value, readOnly, ref, check } }) {
    // eslint-disable-next-line no-return-assign
    return (
        <f.StyledDiv>
            <f.StyledLabel htmlFor={id} isFill={Boolean(value)}>
                {text}
            </f.StyledLabel>
            <f.StyledInput
                type={type}
                id={id}
                name={name}
                onChange={onChangeFunc}
                ref={ref}
                placeholder={(placeholder ??= "")}
                value={value}
                isFill={Boolean(value)}
                readOnly={readOnly === 5}
                check={check === 2 || check === 3 || check === 4}
            ></f.StyledInput>
            <f.StyledButton onClick={onClickFunc} check={check === 2 || check === 3 || check === 4}>
                인증
            </f.StyledButton>
        </f.StyledDiv>
    );
}
