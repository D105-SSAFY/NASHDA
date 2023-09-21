/* eslint-disable no-alert */
import * as s from "./style";
import video1 from "assets/image/nashda_move.mov";
import SignupInput from "components/input/FormInputCol";
// Import { checkEmail, sendCode, checkCode, signUp } from "apis/user";
import { useState } from "react";
import { useNavigate } from "react-router";

// 임시 fetch들
export const checkCode = async ({ email, code }) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/checkcode`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, code })
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const signUp = async ({ email, password, name, nickname, age = null, job = null, hobby = null }) => {
    try {
        const response = fetch(`${process.env.API_URL}/user/signup`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                email,
                password,
                name,
                nickname,
                age,
                job,
                hobby
            }),
            credentials: "include"
        });

        const result = await response.json();

        return result;
    } catch (error) {
        console.log(error);
    }
};

export const checkEmail = async (email) => {
    try {
        const response = await fetch("https://j9d105.p.ssafy.io/api/user/checkemail", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email })
        });

        const result = await response.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
};

export const checkNickname = async (nickname) => {
    try {
        const response = await fetch("https://j9d105.p.ssafy.io/api/user/checknickname", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ nickname })
        });

        const result = await response.json();
        console.log(result);
        return result;
    } catch (error) {
        console.log(error);
    }
};

export const sendCode = async (email) => {
    try {
        const response = await fetch("https://j9d105.p.ssafy.io/api/user/sendcode", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email })
        });

        const result = await response.json();
        return result;
    } catch (error) {
        console.log(error);
    }
};

// 임시 패치들

export default function SignupPage() {
    const [inputs, setInputs] = useState({
        email: "",
        code: "",
        password: "",
        checkedPassword: "",
        name: "",
        nickname: ""
    });
    const [overlapEmail, setOverlapEmail] = useState(false);
    const [overlapNickname, setOverlapNickname] = useState(false);
    const checkEmailText = ["중복된 이메일 입니다!", "사용할 수 없는 이메일 입니다!", "사용할 수 있는 이메일 입니다!"];
    const checkNicknameText = ["중복된 닉네임 입니다!", "사용할 수 없는 닉네임 입니다!", "사용할 수 있는 닉네임 입니다!"];
    // UseEffect(() => {
    //     const overlap = async () => {
    //         if (!inputs.email) {
    //             setOverlapEmail(false);
    //             return;
    //         }

    //         if (!pattern.test(inputs.email)) {
    //             setOverlapEmail(1);
    //             return;
    //         }

    //         const result = await checkEmail(inputs.email);

    //         if (result.status === 200) {
    //             setOverlapEmail(2);
    //         } else {
    //             setOverlapEmail(0);
    //         }
    //     };

    //     overlap();

    //     console.log(overlapEmail);
    // }, [inputs.email]);

    // useEffect(() => {
    //     const overlap = async () => {
    //         if (!inputs.nickname) {
    //             setOverlapNickname(false);
    //             return;
    //         }

    //         const result = await checkNickname(inputs.nickname);

    //         if (result.status === 200) {
    //             setOverlapNickname(2);
    //         } else {
    //             setOverlapNickname(0);
    //         }
    //     };

    //     overlap();

    //     console.log(overlapNickname);
    // }, [inputs.nickname]);

    const navigate = useNavigate();
    const pattern =
        // eslint-disable-next-line no-useless-escape
        /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;

    const handleChange = async (e) => {
        if (e.target.name === "email") {
            if (!e.target.value) {
                setOverlapEmail(false);
                return;
            }

            if (!pattern.test(e.target.value)) {
                setOverlapEmail(1);
                return;
            }

            const result = await checkEmail(e.target.value);

            if (result.status === 200) {
                setOverlapEmail(2);
            } else {
                setOverlapEmail(0);
            }
        }

        if (e.target.name === "nickname") {
            if (!e.target.value) {
                setOverlapNickname(false);
                return;
            }

            const result = await checkNickname(e.target.value);

            if (result.status === 200) {
                setOverlapNickname(2);
            } else {
                setOverlapNickname(0);
            }
        }

        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        });
        console.log(inputs);
    };

    const handleClick = async (e) => {
        e.preventDefault();
        if (!inputs.email) {
            alert("이메일을 입력하세요!");
        }

        const result = await sendCode(inputs.email);

        if (result.status === 200) {
            alert("인증번호를 전송했어요!");
        } else {
            alert("인증번호 전송에 실패했어요!");
        }
    };

    const handleCheck = async (e) => {
        e.preventDefault();
        if (!inputs.email) {
            alert("이메일 입력하세요!!");
            return;
        }

        if (!inputs.code) {
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
            return;
        }

        const checkCodeResult = await checkCode(inputs.email, inputs.code);
        if (!checkCodeResult) {
            alert("인증코드가 일치하지 않습니다!");
            return;
        }

        const result = await signUp(inputs.email, inputs.password, inputs.name, inputs.nickname);
        if (result) {
            alert("회원가입 성공!");
            navigate("/signin");
        } else {
            alert("회원가입에 실패했습니다!");
        }
    };

    const checkEnter = (e) => {
        if (e.keyCode === 13) {
            e.preventDefault();
        }
    };

    return (
        <s.StyledMain>
            <s.StyledMainSection>
                <s.StyledVid src={video1} alt="그림" autoPlay muted loop></s.StyledVid>
                <s.StyledSignupTitle>숨을 내쉬다.</s.StyledSignupTitle>
                <s.StyledForm onKeyDown={checkEnter}>
                    <SignupInput
                        data={{
                            text: "이메일",
                            id: "email",
                            name: "email",
                            type: "email",
                            onChangeFunc: handleChange,
                            onClickFunc: handleClick,
                            check: overlapEmail
                        }}
                    />
                    <SignupInput
                        data={{
                            text: "이메일 인증번호",
                            id: "code",
                            name: "code",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.code
                        }}
                    />
                    <s.StyledText>{checkEmailText[overlapEmail]}</s.StyledText>
                    <s.StyledLine></s.StyledLine>
                    <SignupInput
                        data={{
                            text: "비밀번호",
                            id: "password",
                            name: "password",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.password
                        }}
                    />
                    <SignupInput
                        data={{
                            text: "비밀번호 확인",
                            id: "checkedPassword",
                            name: "checkedPassword",
                            type: "password",
                            onChangeFunc: handleChange,
                            value: inputs.checkedPassword
                        }}
                    />
                    <s.StyledLine></s.StyledLine>
                    <SignupInput
                        data={{
                            text: "본명",
                            id: "name",
                            name: "name",
                            type: "text",
                            onChangeFunc: handleChange,
                            value: inputs.name
                        }}
                    />
                    <SignupInput
                        data={{
                            text: "별명",
                            id: "nickname",
                            name: "nickname",
                            type: "text",
                            onChangeFunc: handleChange
                        }}
                    />
                    <s.StyledText>{checkNicknameText[overlapNickname]}</s.StyledText>
                    <s.StyledSignupBtn onClick={handleCheck}>회원가입 완료</s.StyledSignupBtn>
                </s.StyledForm>
                <s.StyledFooter></s.StyledFooter>
            </s.StyledMainSection>
        </s.StyledMain>
    );
}
