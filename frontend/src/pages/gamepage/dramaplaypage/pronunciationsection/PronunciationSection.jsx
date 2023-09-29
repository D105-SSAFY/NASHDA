import * as s from "./style";

import HintModal from "components/modals/hintmodal/HintModal";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";

import MicIcon from "@mui/icons-material/Mic";
import LightbulbIcon from "@mui/icons-material/Lightbulb";
import RedoIcon from "@mui/icons-material/Redo";
import { useCallback, useEffect, useState } from "react";
import voice from "utils/VoiceFunc";
import VoiceModal from "components/modals/voicemodal/VoiceModal";

export default function PronunciationSection({ props: { problem, sentence } }) {
    const [audioText, setAudioText] = useState("");
    const [onModal, setOnModal] = useState(false);
    const [onHintModal, setOnHintModal] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);

    console.log(audioText);

    const showModal = useCallback(() => {
        setOnModal(true);
    }, []);

    const unshowModal = useCallback(() => {
        setOnModal(false);
    }, []);

    const showHintModal = useCallback(() => {
        setOnHintModal(true);
    }, []);

    const unshowHintModal = useCallback(() => {
        setOnHintModal(false);
    }, []);

    const { onRecAudio, offRecAudio, createFile } = voice();

    const onClickRecordOn = () => {
        showModal();
        onRecAudio();
    };

    const onClickRecordOff = async () => {
        unshowModal();
        offRecAudio();
    };

    const send = async () => {
        const file = createFile();

        if (!file) {
            return false;
        }

        const formData = new FormData();

        formData.append("file_upload", file);

        try {
            const response = await fetch("http://127.0.0.1:8000/file", {
                method: "POST",
                body: formData
            });

            const result = await response.json();

            return result;
        } catch (e) {
            console.log(e);
        }
    };

    useEffect(() => {
        if (onModal) {
            return;
        }

        setOnUpdate(true);
    }, [onModal]);

    useEffect(() => {
        if (!onUpdate) {
            return;
        }

        const getSTT = async () => {
            const result = await send();

            if (!result) {
                return;
            }

            setAudioText(result.result);
        };

        getSTT();

        setOnUpdate(false);
    }, [onUpdate]);

    return (
        <>
            <s.Section>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: onClickRecordOn }}>
                        <MicIcon />
                        <span>녹음하기</span>
                    </FilledButton>
                    <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)", callback: showHintModal }}>
                        <LightbulbIcon />
                        <span>힌트</span>
                    </BorderButton>
                    <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)" }}>
                        <RedoIcon />
                        <span>다음</span>
                    </BorderButton>
                </s.ButtonWrapper>
                <HintModal props={{ problem, display: onHintModal, hideModal: unshowHintModal }} />
            </s.Section>
            <VoiceModal props={{ title: "정답을 말해보세요.", content: sentence, visible: onModal, callback: onClickRecordOff }} />
        </>
    );
}
