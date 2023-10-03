import * as r from "./style";
import video1 from "assets/image/nashda_move.mov";
import ResetpwInput from "components/input/FormInputCol";
import ResetpwModal from "components/modals/signupmodal/SignupModal";
import eetch from "apis/eetch";
import { useState, useRef } from "react";
import { useNavigate } from "react-router";

export default function ResetpwPage() {
    const [inputs, setInputs] = useState({
        email: "",
        code: "",
        password: "",
        checkedPassword: ""
    });

    const [onModal, setOnModal] = useState(false);
    const [onModalText, setOnModalText] = useState("");

    const [isLoading, setIsLoading] = useState(false);
    const [validEmail, setValidEmail] = useState(null);
    const [validPassword, setValidPassword] = useState(null);
    const timeoutIdRef = useRef(null);
    const checkEmailText = [
        "",
        "이메일 형식을 확인하세요!",
        "인증버튼을 눌러주세요!",
        "인증번호를 입력하세요!",
        "인증번호가 일치하지 않습니다!",
        "인증 성공!"
    ];
    const checkPasswordText = [
        "8~16자, 특수문자 1자 이상을 포함해야 합니다!",
        "사용 가능한 비밀번호 입니다!",
        "비밀번호가 일치하지 않습니다!",
        "비밀번호가 일치합니다!"
    ];
    const emailPattern =
        // eslint-disable-next-line no-useless-escape
        /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|~`])[a-zA-Z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|~`]{8,16}$/;

    const navigate = useNavigate();

    const handleChange = (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        });

        if (e.target.name === "email") {
            if (!e.target.value) {
                setValidEmail(null);
                return;
            }

            if (!emailPattern.test(e.target.value)) {
                setValidEmail(1);
                return;
            }

            setValidEmail(2);
        }

        if (e.target.name === "code") {
            if (timeoutIdRef.current) clearTimeout(timeoutIdRef.current);

            if (!e.target.value) {
                setValidEmail(3);
                return;
            }

            timeoutIdRef.current = setTimeout(async () => {
                const result = await eetch.checkCode({ email: inputs.email, code: e.target.value });

                if (result.status === 200) {
                    setValidEmail(5);
                } else {
                    setValidEmail(4);
                }
            }, 400);
        }

        if (e.target.name === "password") {
            if (!e.target.value) {
                setValidPassword(null);
                return;
            }

            if (e.target.value.includes(" ") || !passwordPattern.test(e.target.value)) {
                setValidPassword(0);
                return;
            }

            if (e.target.value === inputs.checkedPassword) {
                setValidPassword(3);
            } else if (inputs.checkedPassword === "") {
                setValidPassword(1);
            } else {
                setValidPassword(2);
            }
        }

        if (e.target.name === "checkedPassword") {
            if (inputs.password === "") {
                setValidPassword(null);
                return;
            }

            if (!passwordPattern.test(inputs.password)) {
                setValidPassword(0);
                return;
            }

            if (!e.target.value) {
                setValidPassword(1);
                return;
            }

            if (e.target.value === inputs.password) {
                setValidPassword(3);
            } else {
                setValidPassword(2);
            }
        }

        console.log(inputs);
    };

    const handleClick = async (e) => {
        e.preventDefault();

        setIsLoading(true);
        const result = await eetch.sendCode({ email: inputs.email });
        setIsLoading(false);

        if (result.status === 200) {
            setOnModal("success");
            setOnModalText("인증번호를 전송했어요!");
            setValidEmail(3);
        } else {
            setOnModal("false");
            setOnModalText("인증번호 전송에 실패했어요!");
        }
    };

    const handleCheck = async (e) => {
        e.preventDefault();

        const result = await eetch.resetPw({ email: inputs.email, newpassword: inputs.password, code: inputs.code });
        console.log(result);
        if (result.status === 200) {
            setOnModal("success");
            setOnModalText("비밀번호 변경 성공!");
        } else {
            setOnModal("false");
            setOnModalText("회원가입에 실패했습니다!");
        }
    };

    const onClickModal = () => {
        if (onModalText === "비밀번호 변경 성공!") {
            setOnModal(false);
            navigate("/signin");
        } else {
            setOnModal(false);
            setOnModalText("");
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
                            onClickFunc: handleClick,
                            check: validEmail,
                            readOnly: validEmail,
                            value: inputs.email,
                            loading: isLoading
                        }}
                    />
                    <ResetpwInput
                        data={{
                            text: "이메일 인증번호",
                            id: "code",
                            name: "code",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.code
                        }}
                    />
                    <r.StyledText colorEmail={validEmail}>{checkEmailText[validEmail]}</r.StyledText>
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
                    <r.StyledText colorPassword={validPassword}>{checkPasswordText[validPassword]}</r.StyledText>
                    <r.StyledResetpwBtn disabled={validEmail !== 5 || validPassword !== 3} onClick={handleCheck}>
                        비밀번호 변경
                    </r.StyledResetpwBtn>
                </r.StyledForm>
                <r.StyledFooter></r.StyledFooter>
            </r.StyledMainSection>
            <ResetpwModal props={{ text: onModalText, visible: onModal, callback: onClickModal }} />
        </r.StyledMain>
    );
}
