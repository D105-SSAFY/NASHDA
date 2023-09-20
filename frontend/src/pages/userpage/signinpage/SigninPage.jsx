import * as s from "./style";
import video1 from "assets/image/nashda_move.mov";
import image2 from "assets/image/signinbtn.png";
import SigninInput from "components/input/FormInputCol";
import { login } from "apis/user";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { loginUser } from "redux/slice/userSlice";
import { useNavigate } from "react-router";

export default function SigninPage() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [inputs, setInputs] = useState({
        email: "",
        password: ""
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
            // eslint-disable-next-line no-alert
            alert("이메일을 입력해주세요!");
            return;
        }

        if (!inputs.password) {
            // eslint-disable-next-line no-alert
            alert("비밀번호를 입력해주세요!");
            return;
        }

        const result = await login(inputs.email, inputs.password);

        if (result) {
            dispatch(
                loginUser({
                    accessToken: result.accessToken
                })
            );
            navigate("/");
        } else {
            // eslint-disable-next-line no-alert
            alert("로그인에 실패했습니다!");
        }
    };

    return (
        <s.StyledMain>
            <s.StyledMainSection>
                <s.StyledVid src={video1} alt="그림" autoPlay muted loop></s.StyledVid>
                <s.StyledSigninTitle>오늘 잘 부탁드릴게요.</s.StyledSigninTitle>
                <s.StyledForm>
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
                    <s.StyledSiginBtn>
                        <s.StyledImg src={image2} alt="로그인" onClick={handleCheck} />
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
