import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";
import VoiceModal from "components/modals/voicemodal/VoiceModal";
import LoadingModal from "components/modals/loadingmodal/LoadingModal";

import MicIcon from "@mui/icons-material/Mic";
import RedoIcon from "@mui/icons-material/Redo";

import voice from "utils/VoiceFunc";
import eetch from "apis/eetch";

export default function PronunciationSection({ props: { problem, getNextProblem, setCorrect, testIndex, problemIndex, setError } }) {
    const [audioText, setAudioText] = useState("");
    const [onModal, setOnModal] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);
    const [loading, setLoading] = useState(false);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();

    const showModal = useCallback(() => {
        setOnModal(true);
    }, []);

    const unshowModal = useCallback(() => {
        setOnModal(false);
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

    useEffect(() => {
        setAudioText("");
    }, [problem]);

    const getSTT = async () => {
        const file = createFile();

        if (!file) {
            return false;
        }

        setLoading(true);

        const formData = new FormData();

        formData.append("sound", file);
        formData.append("index", testIndex);
        formData.append("order", problemIndex + 1);
        formData.append("imgUrl", "");

        const values = {};

        values.user = user;
        values.formData = formData;

        eetch
            .tokenValidation(eetch.weekSubmit, values, dispatch)
            .then((result) => {
                setAudioText(result.data);
                setLoading(false);
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

    const getSentence = () => {
        let sentence = problem.answer;

        problem.word.forEach((word) => {
            sentence = sentence.replace(word, "__");
        });

        return sentence;
    };

    return (
        <>
            <s.Section>
                <s.Header>
                    <h2>나의 발음</h2>
                </s.Header>
                <s.Box>
                    <s.Pron>{audioText}</s.Pron>
                </s.Box>
                <s.ButtonWrapper>
                    <FilledButton props={{ background: "rgba(68, 71, 90, 0.7)", color: "#ffffff", hovercolor: "#44475A", callback: onClickRecordOn }}>
                        <MicIcon />
                        <span>{audioText ? "다시 녹음하기" : "녹음하기"}</span>
                    </FilledButton>
                    <BorderButton
                        props={{
                            color: "rgba(68, 71, 90, 0.7)",
                            callback() {
                                if (audioText === problem.answer) {
                                    setCorrect((correct) => correct + 1);
                                }

                                getNextProblem();
                            }
                        }}
                    >
                        <RedoIcon />
                        <span>다음</span>
                    </BorderButton>
                </s.ButtonWrapper>
                <VoiceModal props={{ title: "정답을 말해보세요.", content: getSentence(), visible: onModal, callback: onClickRecordOff }} />
                {loading ? <LoadingModal /> : <></>}
            </s.Section>
        </>
    );
}
