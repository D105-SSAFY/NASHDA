import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";

import SuccessIcon from "assets/image/accept.png";
import FailIcon from "assets/image/warning.png";

export default function SignupModal({ props: { text, visible, callback } }) {
    return (
        <s.Wrapper visible={visible}>
            <s.Section>
                <header>
                    <h2>회원가입 모달</h2>
                </header>
                {visible === "success" ? <s.SuccessImg src={SuccessIcon}></s.SuccessImg> : <s.SuccessImg src={FailIcon}></s.SuccessImg>}
                <s.Pron>{text}</s.Pron>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgb(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback }}>
                        <span>확인</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
