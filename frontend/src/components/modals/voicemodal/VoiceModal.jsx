import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import SoundWave from "components/soundwave/SoundWave";

import MicOffIcon from "@mui/icons-material/MicOff";

export default function VoiceModal({ props: { pronunciation, visible, offModal, callback } }) {
    const onClickModal = (e) => {
        e.stopPropagation();
    };

    return (
        <s.Wrapper visible={visible} onClick={offModal}>
            <s.Section onClick={onClickModal}>
                <header>
                    <h2>음성 입력 모달</h2>
                </header>
                <s.Read>따라 읽어 보세요.</s.Read>
                <s.Pron>&quot;{pronunciation}&quot;</s.Pron>
                <SoundWave props={{ start: visible }} />
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", callback }}>
                        <MicOffIcon />
                        <span>녹음 완료</span>
                    </FilledButton>
                </s.ButtonWrapper>
            </s.Section>
        </s.Wrapper>
    );
}
