import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

export default function ErrorModal({ props: { title, content, display, callback } }) {
    return (
        <s.Wrapper visible={display}>
            <s.Section>
                <header>
                    <h2>에러 모달</h2>
                </header>
                <s.Title>{title}</s.Title>
                <s.Content>{content}</s.Content>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback }}>
                        <ExitToAppIcon />
                        <span>힌트 닫기</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
