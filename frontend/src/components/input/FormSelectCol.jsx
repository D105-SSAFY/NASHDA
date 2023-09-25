import * as f from "./style";

export default function FormSelectCol({ data: { text, type, id, name, onChangeFunc, onClickFunc, placeholder, value, readOnly, ref, check, list } }) {
    // List?.hobbyList가 정의되었는지 확인 후, 안전하게 접근합니다.
    const { jobList = [], hobbyList = [] } = list;

    // eslint-disable-next-line no-return-assign
    return (
        <f.StyledDiv>
            <f.StyledLabel htmlFor={id}>{text}</f.StyledLabel>
            <f.StyledSelect
                type={type}
                id={id}
                name={name}
                onChange={onChangeFunc}
                ref={ref}
                placeholder={(placeholder ??= "")}
                value={value}
                readOnly={(readOnly ??= false)}
                check={check === 2}
            >
                <f.StyledOption value={null}>없음</f.StyledOption>
                {(text === "직업" ? jobList : hobbyList).map((item) => (
                    <f.StyledOption key={item.jobIdx || item.hobbyIdx} value={item.jobIdx || item.hobbyIdx}>
                        {item.job || item.hobby}
                    </f.StyledOption>
                ))}
            </f.StyledSelect>
            <f.StyledButton onClick={onClickFunc} check={check === 2}>
                인증
            </f.StyledButton>
        </f.StyledDiv>
    );
}
