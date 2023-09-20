/* eslint-disable no-alert */
import * as r from "./style";
import video1 from "assets/image/nashda_move.mov";
import ResetpwInput from "components/input/FormInputCol";
import { useState } from "react";

export default function SignupPage() {
    const [inputs, setInputs] = useState({
        email: "",
        certificatedNumber: "",
        password: "",
        checkedPassword: ""
    });

    const handleChange = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        });

        console.log(inputs);
    };

    const handleCheck = async (e) => {
        e.preventDefault();

        if (!inputs.email) {
            alert("이메일 입력하세요!!");
            return;
        }

        if (!inputs.certificatedNumber) {
            alert("인증번호 입력하세요!");
            return;
        }

        if (!inputs.password) {
            alert("비밀번호 입력하세요!");
            return;
        }

        if (!inputs.checkedPassword) {
            alert("비밀번호확인 입력하세요!");
        }
    };

    return (
        <r.StyledMain>
            <r.StyledMainSection>
                <r.StyledVid src={video1} alt="그림" autoPlay muted loop></r.StyledVid>
                <r.StyledResetpwTitle>걱정마세요. 저희가 찾아드릴게요.</r.StyledResetpwTitle>
                <r.StyledForm>
                    <ResetpwInput
                        data={{
                            text: "이메일",
                            id: "email",
                            name: "email",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.email,
                            check: true
                        }}
                    />
                    <ResetpwInput
                        data={{
                            text: "이메일 인증번호",
                            id: "certificatedNumber",
                            name: "certificatedNumber",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.certificatedNumber
                        }}
                    />
                    <r.StyledLine></r.StyledLine>
                    <ResetpwInput
                        data={{
                            text: "새 비밀번호",
                            id: "password",
                            name: "password",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.password
                        }}
                    />
                    <ResetpwInput
                        data={{
                            text: "새 비밀번호 확인",
                            id: "checkedPassword",
                            name: "checkedPassword",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.checkedPassword
                        }}
                    />
                    <r.StyledResetpwBtn onClick={handleCheck}>비밀번호 변경</r.StyledResetpwBtn>
                </r.StyledForm>
                <r.StyledFooter></r.StyledFooter>
            </r.StyledMainSection>
        </r.StyledMain>
    );
}
