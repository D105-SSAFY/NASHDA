import { useCallback, useEffect, useState } from "react";

import * as s from "./style";

import voice from "utils/VoiceFunc";

import VoiceModal from "components/modals/voicemodal/VoiceModal";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";

import MicIcon from "@mui/icons-material/Mic";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";

export default function PronunciationSection({ props: { pronunciation } }) {
    const [audioText, setAudioText] = useState("");
    const [onModal, setOnModal] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);

    const checkProun = useCallback(() => {
        if (!pronunciation || !audioText) {
            return [];
        }

        const pronunciationWords = pronunciation.replaceAll(" ", "").split("");
        const testWords = audioText.replaceAll(" ", "").split("");
        const result = pronunciationWords.reduce((acc, cur, idx) => {
            if (cur === testWords[idx]) {
                acc.push({
                    word: testWords[idx],
                    match: true
                });
            } else {
                acc.push({
                    word: testWords[idx],
                    match: false
                });
            }

            return acc;
        }, []);

        if (testWords.length > pronunciationWords.length) {
            testWords.splice(pronunciationWords.length, testWords.length).forEach((word) => {
                result.push({
                    word,
                    match: false
                });
            });
        }

        return result;
    }, [pronunciation, audioText]);

    const showModal = useCallback(() => {
        setOnModal(true);
    }, []);

    const unshowModal = useCallback(() => {
        setOnModal(false);
    }, []);

    const { onRecAudio, offRecAudio, createFile, play } = voice();

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
                <s.Header>
                    <h2>나의 발음</h2>
                </s.Header>
                <s.Box>
                    <s.Pron>
                        {checkProun().map((word, index) =>
                            word.match ? <s.Match key={word + index}>{word.word}</s.Match> : <s.Unmatch key={word + index}>{word.word}</s.Unmatch>
                        )}
                    </s.Pron>
                    <s.SpeakButton visible={Boolean(audioText)} onClick={play}>
                        <VolumeUpIcon />
                    </s.SpeakButton>
                </s.Box>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: onClickRecordOn }}>
                        <MicIcon />
                        <span>녹음하기</span>
                    </FilledButton>
                    <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)" }}>
                        <span>다음</span>
                    </BorderButton>
                </s.ButtonWrapper>
            </s.Section>
            <VoiceModal props={{ pronunciation, visible: onModal, callback: onClickRecordOff }} />
        </>
    );
}
