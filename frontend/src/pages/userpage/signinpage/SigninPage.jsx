import * as Styled from "./style";
import video1 from "../../../assets/image/nashda_move.mov";
import image2 from "../../../assets/image/signinbtn.png";
import SigninInput from "../../../components/input/SigninInput";
import { Signin } from "../../../services/UserServices";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { loginUser } from "redux/slice/userSlice";
import { useNavigate } from "react-router";

export default function SigninPage() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [inputs, setInputs] = useState({
        id: "",
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

        if (!inputs.id) {
            // eslint-disable-next-line no-alert
            alert("아이디를 입력해주세요!");
            return;
        }

        if (!inputs.password) {
            // eslint-disable-next-line no-alert
            alert("비밀번호를 입력해주세요!");
            return;
        }

        // eslint-disable-next-line new-cap
        const result = await Signin(inputs.id, inputs.password);

        if (result) {
            dispatch(
                loginUser({
                    accessToken: result.accessToken
                })
            );
            navigate("/");
        }
    };

    return (
        <Styled.StyledMain>
            <header style={{ height: "5%" }}>내쉬다</header>
            <Styled.StyledMainSection>
                <Styled.StyledVid src={video1} alt="그림" width="100%" height="55%" autoPlay muted loop></Styled.StyledVid>
                <Styled.StyledSigninTitle>오늘 잘 부탁드릴게요.</Styled.StyledSigninTitle>
                <Styled.StyledForm>
                    <SigninInput
                        data={{
                            text: "이메일",
                            id: "id",
                            name: "id",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.id
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
                    <Styled.StyledSiginBtn>
                        <Styled.StyledImg src={image2} alt="로그인" onClick={handleCheck} />
                    </Styled.StyledSiginBtn>
                </Styled.StyledForm>

                <Styled.StyledTextSection>
                    <Styled.StyledText size="14px">비밀번호 찾기</Styled.StyledText>
                    <Styled.StyledText size="14px">회원가입</Styled.StyledText>
                </Styled.StyledTextSection>
                <Styled.StyledFooter></Styled.StyledFooter>
            </Styled.StyledMainSection>
        </Styled.StyledMain>
    );
}
