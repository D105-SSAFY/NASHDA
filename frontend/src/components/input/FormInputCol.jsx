import * as f from "./style";
import loadingSpinner from "assets/image/Spinner-1s-45px.gif";
import { useState } from "react";
export default function FormInputCol({
    data: { text, type, id, name, onChangeFunc, onClickFunc, placeholder, value, readOnly, ref, check, loading }
}) {
    const [isFocused, setIsFocused] = useState(false);

    const handleFocus = () => {
        setIsFocused(true);
    };

    const handleBlur = () => {
        setIsFocused(false);
    };

    // eslint-disable-next-line no-return-assign
    return (
        <f.StyledDiv>
            <f.StyledLabel htmlFor={id} isFill={Boolean(value) || isFocused}>
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
                readOnly={readOnly === 5}
                check={check === 2 || check === 3 || check === 4}
                onFocus={handleFocus}
                onBlur={handleBlur}
            ></f.StyledInput>
            {loading ? (
                <f.StyledImg src={loadingSpinner}></f.StyledImg>
            ) : (
                <f.StyledButton onClick={onClickFunc} check={check === 2 || check === 3 || check === 4}>
                    인증
                </f.StyledButton>
            )}
        </f.StyledDiv>
    );
}
