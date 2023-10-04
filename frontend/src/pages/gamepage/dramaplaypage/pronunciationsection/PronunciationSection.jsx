import { useCallback, useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import * as s from "./style";

import HintModal from "components/modals/hintmodal/HintModal";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";
import VoiceModal from "components/modals/voicemodal/VoiceModal";

import MicIcon from "@mui/icons-material/Mic";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import LightbulbIcon from "@mui/icons-material/Lightbulb";
import RedoIcon from "@mui/icons-material/Redo";

import voice from "utils/VoiceFunc";
import eetch from "apis/eetch";

export default function PronunciationSection({
    props: { problemList, problemIndex, sentence, setProblemIndex, setCorrect, hint, showHint, onHintModal, setOnHintModal, setError }
}) {
    const [audioText, setAudioText] = useState({
        text: "",
        correct: false
    });
    const [onModal, setOnModal] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);

    const timerRef = useRef(null);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();

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

    const { onRecAudio, offRecAudio, createFile, play } = voice();

    const onClickRecordOn = () => {
        showModal();
        onRecAudio();
    };

    const onClickRecordOff = async () => {
        unshowModal();
        offRecAudio();
    };

    useEffect(() => {
        setAudioText({
            text: "",
            correct: false
        });
    }, [problemIndex]);

    useEffect(() => {
        if (!audioText.correct) {
            return;
        }

        setCorrect((correct) => correct + 1);

        timerRef.current = setTimeout(() => {
            setProblemIndex((index) => index + 1);
        }, 1000);
    }, [audioText]);

    const getSTT = async () => {
        const file = createFile();

        if (!file) {
            return false;
        }

        const formData = new FormData();

        formData.append("sound", file);
        formData.append("answer", problemList[problemIndex].answer);
        formData.append("type", 2);

        const values = {};

        values.user = user;
        values.formData = formData;

        eetch
            .tokenValidation(eetch.game, values, dispatch)
            .then((result) => {
                setAudioText({
                    text: result.data.convert,
                    correct: result.data.result
                });
            })
            .catch(() => {
                setError(true);
            });
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
                    <s.Pron correct={audioText.correct}>{audioText.text}</s.Pron>
                    <s.SpeakButton visible={Boolean(audioText.text)} onClick={play}>
                        <VolumeUpIcon />
                    </s.SpeakButton>
                </s.Box>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: onClickRecordOn }}>
                        <MicIcon />
                        <span>{audioText.text ? "다시 녹음하기" : "녹음하기"}</span>
                    </FilledButton>
                    <s.HintWrapper showHint={showHint}>
                        <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)", callback: showHintModal }}>
                            <LightbulbIcon />
                            <span>힌트</span>
                        </BorderButton>
                    </s.HintWrapper>
                    <BorderButton
                        props={{
                            color: "rgba(68, 71, 90, 0.7)",
                            callback() {
                                if (timerRef.current) {
                                    clearTimeout(timerRef.current);
                                }

                                setProblemIndex((index) => index + 1);
                            }
                        }}
                    >
                        <RedoIcon />
                        <span>다음</span>
                    </BorderButton>
                </s.ButtonWrapper>
                {problemList.length !== 0 && problemIndex !== -1 && <HintModal props={{ hint, display: onHintModal, hideModal: unshowHintModal }} />}
                <VoiceModal props={{ title: "정답을 말해보세요.", content: sentence, visible: onModal, callback: onClickRecordOff }} />
            </s.Section>
        </>
    );
}
