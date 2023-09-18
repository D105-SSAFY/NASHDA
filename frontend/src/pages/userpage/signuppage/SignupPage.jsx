/* eslint-disable no-alert */
import * as Styled from "./style";
import video1 from "../../../assets/image/nashda_move.mov";
import SigninInput from "../../../components/input/SigninInput";
import { useState } from "react";

export default function SignupPage() {
    const [inputs, setInputs] = useState({
        id: "",
        certificatedNumber: "",
        password: "",
        checkedPassword: "",
        name: "",
        nickname: "",
    });

    const handleChange = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value,
        });

        console.log(inputs);
    };

    const handleCheck = async (e) => {
        e.preventDefault();

        if (!inputs.id) {
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
            return;
        }

        if (!inputs.name) {
            alert("이름 입력하세요!");
            return;
        }

        if (!inputs.nickname) {
            alert("닉네임 입력하세요!");
        }
    };

    return (
        <Styled.StyledMain>
            <Styled.StyledMainSection>
                <Styled.StyledVid
                    src={video1}
                    alt="그림"
                    width="100%"
                    height="450px"
                    autoPlay
                    muted
                    loop
                ></Styled.StyledVid>
                <Styled.StyledSigninTitle>
                    숨을 내쉬다.
                </Styled.StyledSigninTitle>
                <Styled.StyledForm>
                    <SigninInput
                        data={{
                            text: "이메일",
                            id: "id",
                            name: "id",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.id,
                            check: true,
                        }}
                    />
                    <SigninInput
                        data={{
                            text: "이메일 인증번호",
                            id: "certificatedNumber",
                            name: "certificatedNumber",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.certificatedNumber,
                        }}
                    />
                    <Styled.StyledLine></Styled.StyledLine>
                    <SigninInput
                        data={{
                            text: "비밀번호",
                            id: "password",
                            name: "password",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.password,
                        }}
                    />
                    <SigninInput
                        data={{
                            text: "비밀번호 확인",
                            id: "checkedPassword",
                            name: "checkedPassword",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.checkedPassword,
                        }}
                    />
                    <Styled.StyledLine></Styled.StyledLine>
                    <SigninInput
                        data={{
                            text: "본명",
                            id: "name",
                            name: "name",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.name,
                        }}
                    />
                    <SigninInput
                        data={{
                            text: "별명",
                            id: "nickname",
                            name: "nickname",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.nickname,
                        }}
                    />
                    <Styled.StyledSignupBtn onClick={handleCheck}>
                        회원가입 완료
                    </Styled.StyledSignupBtn>
                </Styled.StyledForm>
                <Styled.StyledFooter></Styled.StyledFooter>
            </Styled.StyledMainSection>
        </Styled.StyledMain>
    );
}
