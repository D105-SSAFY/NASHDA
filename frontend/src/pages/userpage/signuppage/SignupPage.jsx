/* eslint-disable no-alert */
import * as s from "./style";
import video1 from "assets/image/nashda_move.mov";
import SignupInput from "components/input/FormInputCol";
import FormSelectCol from "components/input/FormSelectCol";
// Import { checkEmail, sendCode, checkCode, signUp } from "apis/user";
import eetch from "apis/eetch";
import { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router";

export default function SignupPage() {
    const [isLoading, setIsLoading] = useState(false);

    const [inputs, setInputs] = useState({
        email: "",
        code: "",
        name: "",
        nickname: ""
    });

    const [inputs2, setInputs2] = useState({
        password: "",
        checkedPassword: "",
        hobby: "",
        job: ""
    });

    const [overlapEmail, setOverlapEmail] = useState(null);
    const [overlapNickname, setOverlapNickname] = useState(null);
    const [overlapPassword2, setOverlapPassword2] = useState(null);
    const timeoutIdRef = useRef(null);
    const checkEmailText = [
        "*중복된 이메일 입니다!",
        "*이메일 형식을 확인하세요!",
        "*사용 가능한 이메일 입니다!",
        "*인증번호를 입력하세요!",
        "*인증번호가 일치하지 않습니다!",
        "*인증 성공!"
    ];
    const checkNicknameText = ["*중복된 닉네임 입니다!", "*2~6자, 특수문자 사용 X", "*사용할 수 있는 닉네임 입니다!"];
    const checkPassword2Text = [
        "*8~16자, 특수문자 1자 이상을 포함해야 합니다!",
        "*사용 가능한 비밀번호 입니다!",
        "*비밀번호가 일치하지 않습니다!",
        "*비밀번호가 일치합니다!"
    ];
    const [domainList, setDomainList] = useState([]);
    const navigate = useNavigate();

    const emailPattern =
        // eslint-disable-next-line no-useless-escape
        /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|~`])[a-zA-Z\d!@#$%^&*()\-_=+[\]{};:'",.<>/?\\|~`]{8,16}$/;
    const nicknamePattern = /^[\u3131-\u318E\uAC00-\uD7A3a-zA-Z\d]{2,6}$/;

    useEffect(() => {
        async function fetchData() {
            const result = await eetch.domain();
            setDomainList(result.data);
        }

        fetchData();
        console.log(domainList.jobList);
    }, []);

    const handleChange = async (e) => {
        setInputs({
            ...inputs,
            [e.target.name]: e.target.value
        });
        console.log(inputs);

        if (e.target.name === "email") {
            if (timeoutIdRef.current) clearTimeout(timeoutIdRef.current);

            if (!e.target.value) {
                setOverlapEmail(null);
                return;
            }

            if (!emailPattern.test(e.target.value)) {
                setOverlapEmail(1);
                return;
            }

            timeoutIdRef.current = setTimeout(async () => {
                const result = await eetch.checkEmail({ email: e.target.value });

                if (result.status === 200) {
                    setOverlapEmail(2);
                } else {
                    setOverlapEmail(0);
                }
            }, 400);
        }

        if (e.target.name === "code") {
            if (timeoutIdRef.current) clearTimeout(timeoutIdRef.current);

            if (!e.target.value) {
                setOverlapEmail(3);
                console.log("여기");
                return;
            }

            timeoutIdRef.current = setTimeout(async () => {
                const result = await eetch.checkCode({ email: inputs.email, code: e.target.value });

                if (result.status === 200) {
                    setOverlapEmail(5);
                } else {
                    setOverlapEmail(4);
                }
            }, 400);
        }

        if (e.target.name === "nickname") {
            if (timeoutIdRef.current) clearTimeout(timeoutIdRef.current);

            if (!e.target.value) {
                setOverlapNickname(null);
                return;
            }

            if (e.target.value.includes(" ") || !nicknamePattern.test(e.target.value)) {
                console.log("여기여기");
                setOverlapNickname(1);
                return;
            }

            timeoutIdRef.current = setTimeout(async () => {
                const result = await eetch.checkNickname({ nickname: e.target.value });

                if (result.status === 200) {
                    setOverlapNickname(2);
                } else {
                    setOverlapNickname(0);
                }
            }, 400);
        }
    };

    const handleChange2 = async (e) => {
        setInputs2({
            ...inputs2,
            [e.target.name]: e.target.value
        });
        console.log(inputs2);

        if (e.target.name === "password") {
            if (!e.target.value) {
                setOverlapPassword2(null);
                return;
            }

            if (e.target.value.includes(" ") || !passwordPattern.test(e.target.value)) {
                setOverlapPassword2(0);
                return;
            }

            if (e.target.value === inputs2.checkedPassword) {
                setOverlapPassword2(3);
            } else if (inputs2.checkedPassword === "") {
                setOverlapPassword2(1);
            } else {
                setOverlapPassword2(2);
            }
        }

        if (e.target.name === "checkedPassword") {
            if (inputs2.password === "") {
                setOverlapPassword2(null);
                return;
            }

            if (!passwordPattern.test(inputs2.password)) {
                setOverlapPassword2(0);
                return;
            }

            if (!e.target.value) {
                setOverlapPassword2(1);
                return;
            }

            if (e.target.value === inputs2.password) {
                setOverlapPassword2(3);
            } else {
                setOverlapPassword2(2);
            }
        }
    };

    const handleClick = async (e) => {
        e.preventDefault();

        setIsLoading(true);
        const result = await eetch.sendCode({ email: inputs.email });
        setIsLoading(false);

        if (result.status === 200) {
            alert("인증번호를 전송했어요!");
            setOverlapEmail(3);
        } else {
            alert("인증번호 전송에 실패했어요!");
        }
    };

    const handleCheck = async (e) => {
        e.preventDefault();

        if (!inputs.name || inputs.name.includes(" ")) {
            alert("사용할 수 없는 이름입니다!");
            return;
        }

        const result = await eetch.signup({
            email: inputs.email,
            password: inputs2.password,
            name: inputs.name,
            nickname: inputs.nickname,
            jobIdx: Number(inputs2.job),
            hobbyIdx: Number(inputs2.hobby)
        });
        console.log(result);
        if (result.status === 200) {
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

    const jobAndHobbyCheck = (target, idx) => {
        if (target === "직업") {
            setInputs2({
                ...inputs2,
                job: idx
            });
        } else {
            setInputs2({
                ...inputs2,
                hobby: idx
            });
        }

        console.log(inputs2);
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
                            check: overlapEmail,
                            readOnly: overlapEmail,
                            value: inputs.email,
                            loading: isLoading
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
                    <s.StyledText colorEmail={overlapEmail}>{checkEmailText[overlapEmail]}</s.StyledText>
                    <s.StyledLine></s.StyledLine>
                    <SignupInput
                        data={{
                            text: "비밀번호",
                            id: "password",
                            name: "password",
                            type: "password",
                            onChangeFunc: handleChange2,
                            value: inputs2.password
                        }}
                    />
                    <SignupInput
                        data={{
                            text: "비밀번호 확인",
                            id: "checkedPassword",
                            name: "checkedPassword",
                            type: "password",
                            onChangeFunc: handleChange2,
                            value: inputs2.checkedPassword
                        }}
                    />
                    <s.StyledText colorPassword={overlapPassword2}>{checkPassword2Text[overlapPassword2]}</s.StyledText>
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
                            onChangeFunc: handleChange,
                            value: inputs.nickname
                        }}
                    />
                    <s.StyledText colorNickname={overlapNickname}>{checkNicknameText[overlapNickname]}</s.StyledText>
                    <s.StyledLine></s.StyledLine>

                    <FormSelectCol data={{ list: domainList.jobList, callback: jobAndHobbyCheck, target: "직업" }} />
                    <FormSelectCol data={{ list: domainList.hobbyList, callback: jobAndHobbyCheck, target: "취미" }} />

                    <s.StyledSignupBtn disabled={overlapEmail !== 5 || overlapNickname !== 2 || overlapPassword2 !== 3} onClick={handleCheck}>
                        회원가입 완료
                    </s.StyledSignupBtn>
                </s.StyledForm>
                <s.StyledFooter></s.StyledFooter>
            </s.StyledMainSection>
        </s.StyledMain>
    );
}
