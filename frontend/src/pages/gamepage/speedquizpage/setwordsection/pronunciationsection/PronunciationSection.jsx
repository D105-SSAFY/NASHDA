import { useCallback, useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";
import VoiceModal from "components/modals/voicemodal/VoiceModal";

import MicIcon from "@mui/icons-material/Mic";
import RedoIcon from "@mui/icons-material/Redo";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";

import voice from "utils/VoiceFunc";
import eetch from "apis/eetch";

export default function PronunciationSection({ props: { problem, getNextProblem, setCorrect } }) {
    const [audioText, setAudioText] = useState({
        text: "",
        correct: false
    });
    const [onModal, setOnModal] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);

    const timerRef = useRef(null);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

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

    useEffect(() => {
        if (!audioText.correct) {
            return;
        }

        setCorrect((correct) => correct + 1);

        timerRef.current = setTimeout(() => {
            setAudioText({
                text: "",
                correct: false
            });

            getNextProblem();
        }, 1000);
    }, [audioText]);

    const getSTT = async () => {
        const file = createFile();

        if (!file) {
            return false;
        }

        const formData = new FormData();

        formData.append("sound", file);
        formData.append("answer", problem.answer);
        formData.append("index", problem.index);
        formData.append("type", problem.type - 1);

        const values = {};

        values.user = user;
        values.formData = formData;

        eetch
            .tokenValidation(eetch.game, values, dispatch)
            .then((result) => {
                // console.log(result);

                setAudioText({
                    text: result.data.convert,
                    correct: result.data.result
                });
            })
            .catch(() => {
                navigate("/signin");
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
                    <h3>나의 발음</h3>
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
                    <BorderButton
                        props={{
                            color: "rgba(68, 71, 90, 0.7)",
                            callback() {
                                if (timerRef.current) {
                                    clearTimeout(timerRef.current);
                                }

                                setAudioText({
                                    text: "",
                                    correct: false
                                });
                                getNextProblem();
                            }
                        }}
                    >
                        <RedoIcon />
                        <span>다음</span>
                    </BorderButton>
                </s.ButtonWrapper>
            </s.Section>
            <VoiceModal props={{ title: "단어를 말해보세요.", content: "", visible: onModal, callback: onClickRecordOff }} />
        </>
    );
}
