import { useCallback, useEffect, useState } from "react";

import * as s from "./style";

import voice from "utils/VoiceFunc";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import SoundWave from "components/soundwave/SoundWave";

import MicIcon from "@mui/icons-material/Mic";
import MicOffIcon from "@mui/icons-material/MicOff";

export default function VoiceSection({ props: { moveToEnd, updateConvs } }) {
    const [onRecord, setOnRecord] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);

    const startRecord = useCallback(() => {
        setOnRecord(true);
    }, []);

    const stopRecord = useCallback(() => {
        setOnRecord(false);
    }, []);

    const { onRecAudio, offRecAudio, createFile } = voice();

    const onClickRecordOn = () => {
        startRecord();
        onRecAudio();
    };

    const onClickRecordOff = async () => {
        stopRecord();
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
        if (onRecord) {
            return;
        }

        setOnUpdate(true);
    }, [onRecord]);

    useEffect(() => {
        if (!onUpdate) {
            return;
        }

        const getSTT = async () => {
            const result = await send();

            if (!result) {
                return;
            }

            console.log(result, updateConvs);

            // 나중에 문장 업데이트 해주기
        };

        getSTT();

        setOnUpdate(false);
    }, [onUpdate]);

    useEffect(() => {
        moveToEnd();
    }, [onRecord]);

    return (
        <s.Section>
            <header>
                <h3>음성 입력 영역</h3>
            </header>
            <s.SoundWrapper visible={onRecord}>
                <s.SoundText>적절한 문장을 말하세요!</s.SoundText>
                <SoundWave props={{ start: onRecord }} />
            </s.SoundWrapper>
            <s.ButtonWrapper>
                {onRecord ? (
                    <FilledButton
                        props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: onClickRecordOff }}
                    >
                        <MicOffIcon />
                        <span>녹음 종료</span>
                    </FilledButton>
                ) : (
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: onClickRecordOn }}>
                        <MicIcon />
                        <span>녹음하기</span>
                    </FilledButton>
                )}
            </s.ButtonWrapper>
        </s.Section>
    );
}
