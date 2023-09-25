/* eslint-disable no-alert */
import * as s from "./style";
import video1 from "assets/image/nashda_move.mov";
import image2 from "assets/image/signinbtn.png";
import SigninInput from "components/input/FormInputCol";
// Import { login } from "apis/user";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { loginUser } from "redux/slice/userSlice";
import { useNavigate } from "react-router";

// 임시 사용
export const login = async ({ email, password }) => {
    try {
        const response = await fetch("https://j9d105.p.ssafy.io/api/user/signin", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        });

        const result = await response.json();
        if (result.errorCode === 4001) {
            return;
        }

        if (result.errorCode === 4002) {
            return;
        }

        return result;
    } catch (error) {
        console.log(error);
    }
};
//

export default function SigninPage() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [inputs, setInputs] = useState({
        email: "",
        password: ""
    });

    // Input에 0.3초 이상 연속 입력이 되지 않을 경우에 console.log('hello') 출력하는 이벤트 핸들러

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
            alert("이메일을 입력해주세요!");
            return;
        }

        if (!inputs.password) {
            alert("비밀번호를 입력해주세요!");
            return;
        }

        const result = await login({ email: inputs.email, password: inputs.password });
        if (result) {
            dispatch(
                loginUser({
                    accessToken: result.data.accessToken,
                    refreshToken: result.data.refreshToken
                })
            );
            navigate("/");
        } else {
            alert("로그인에 실패했습니다!");
        }
    };

    return (
        <s.StyledMain>
            <s.StyledMainSection>
                <s.StyledVid src={video1} alt="그림" autoPlay muted loop></s.StyledVid>
                <s.StyledSigninTitle>오늘 잘 부탁드릴게요.</s.StyledSigninTitle>
                <s.StyledForm onSubmit={(e) => e.preventDefault()}>
                    <SigninInput
                        data={{
                            text: "이메일",
                            id: "email",
                            name: "email",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.email
                        }}
                    />
                    <SigninInput
                        data={{
                            text: "비밀번호",
                            id: "password",
                            name: "password",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.password
                        }}
                    />
                    <s.StyledSiginBtn onClick={handleCheck}>
                        <s.StyledImg src={image2} alt="로그인" />
                    </s.StyledSiginBtn>
                </s.StyledForm>

                <s.StyledLinkSection>
                    <s.StyledLink to="/resetpw">비밀번호 찾기</s.StyledLink>
                    <s.StyledLink to="/signup">회원가입</s.StyledLink>
                </s.StyledLinkSection>
            </s.StyledMainSection>
            <s.StyledFooter></s.StyledFooter>
        </s.StyledMain>
    );
}
