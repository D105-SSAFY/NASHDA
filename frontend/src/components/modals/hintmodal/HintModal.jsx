import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

export default function HintModal({ props: { hint, display, hideModal } }) {
    return (
        <s.Wrapper visible={display} onClick={hideModal}>
            <s.Section onClick={(e) => e.stopPropagation()}>
                <header>
                    <h3>힌트</h3>
                </header>
                <s.List>
                    {hint.map((hint, index) => {
                        return (
                            <s.ListItem key={String(index) + hint[0]}>
                                <p>{index + 1}번째 단어</p>
                                <p>1. 이 단어는 {hint[0]}입니다.</p>
                                <p>2. {hint[1]}</p>
                            </s.ListItem>
                        );
                    })}
                </s.List>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: hideModal }}>
                        <ExitToAppIcon />
                        <span>힌트 닫기</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
