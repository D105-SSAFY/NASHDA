import * as s from "./style";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import SuccessIcon from "assets/image/icons8-승인-144.png";
import FailIcon from "assets/image/icons8-오류-96.png";

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
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback }}>
                        <span>확인</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
