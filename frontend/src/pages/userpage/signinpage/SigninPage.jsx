import * as s from "./style";
import video1 from "assets/image/nashda_move.mov";
import image2 from "assets/image/signinbtn.png";
import SigninInput from "components/input/FormInputCol";
import SigninModal from "components/modals/signupmodal/SignupModal";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { loginUser } from "redux/slice/userSlice";
import { useNavigate } from "react-router";
import eetch from "apis/eetch";

export default function SigninPage() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [onModal, setOnModal] = useState(false);
    const [onModalText, setOnModalText] = useState("");

    const [inputs, setInputs] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        });
    };

    const handleCheck = async (e) => {
        e.preventDefault();

        if (!inputs.email) {
            setOnModal("false");
            setOnModalText("이메일을 입력해주세요!");
            return;
        }

        if (!inputs.password) {
            setOnModal("false");
            setOnModalText("비밀번호를 입력해주세요!");
            return;
        }

        const result = await eetch.signin({ email: inputs.email, password: inputs.password });
        if (result.status === 200) {
            dispatch(
                loginUser({
                    accessToken: result.data.accessToken,
                    refreshToken: result.data.refreshToken
                })
            );
            navigate("/");
        } else {
            setOnModal("false");
            setOnModalText("아이디 또는 비밀번호를 잘못 입력했습니다!");
        }
    };

    const onClickModal = () => {
        setOnModal(false);
        setOnModalText("");
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
            <SigninModal props={{ text: onModalText, visible: onModal, callback: onClickModal }} />
        </s.StyledMain>
    );
}
