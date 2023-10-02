import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import SoundWave from "components/soundwave/SoundWave";

import MicOffIcon from "@mui/icons-material/MicOff";

export default function VoiceModal({ props: { title, content, visible, callback } }) {
    return (
        <s.Wrapper visible={visible}>
            <s.Section>
                <header>
                    <h3>음성 입력 모달</h3>
                </header>
                <s.Read>{title}</s.Read>
                {content ? <s.Pron>&quot;{content}&quot;</s.Pron> : <></>}
                <SoundWave props={{ start: visible }} />
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback }}>
                        <MicOffIcon />
                        <span>녹음 완료</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
