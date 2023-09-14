import * as Styled from "./style";
import video1 from "../../../assets/image/nashda_move.mov";
import image2 from "../../../assets/image/signinbtn.png";
import SigninInput from "../../../components/input/SigninInput";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { loginUser } from "redux/slice/userSlice";

export default function SigninPage() {
    const dispatch = useDispatch();

    const [inputs, setInputs] = useState({
        id: "",
        password: "",
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
      
      if(!inputs.id){
        alert('아이디를 입력해주세요!');
        return
      }
      if(!inputs.password){
        alert('비밀번호를 입력해주세요!');
        return
      }

      const result = await Signin(inputs.id, inputs.password)

      if (result) {
        dispatch(loginUser({
          accessToken : result[]
        }))
      }
    };

    return (
        <div
            style={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                height: "100vh",
            }}
        >
            <header style={{ height: "5%" }}>내쉬다</header>
            <Styled.styledMain>
                <Styled.styledVid
                    src={video1}
                    alt="그림"
                    width="100%"
                    height="55%"
                    autoPlay
                    muted
                    loop
                ></Styled.styledVid>
                <Styled.styledTitle>오늘 잘 부탁드릴게요.</Styled.styledTitle>
                <SigninInput
                    data={{
                        text: "이메일",
                        id: "id",
                        name: "id",
                        type: "text",
                        onChangeFunc: handleChange,
                        value: inputs.id,
                    }}
                />
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
                <Styled.styledImg
                    src={image2}
                    alt="로그인"
                    height="56px"
                    style={{ marginLeft: "185px", marginTop: "25px" }}
                    onClick={handleCheck}
                />

                <Styled.styledTextSection>
                    <Styled.styledText size="14px">
                        비밀번호 찾기
                    </Styled.styledText>
                    <Styled.styledText size="14px">회원가입</Styled.styledText>
                </Styled.styledTextSection>
                <Styled.styledFooter></Styled.styledFooter>
            </Styled.styledMain>
        </div>
    );
}
