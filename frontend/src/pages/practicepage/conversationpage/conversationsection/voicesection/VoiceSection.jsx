import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import FilledButton from "components/buttons/filledbutton/FilledButton";
import SoundWave from "components/soundwave/SoundWave";
import BorderButton from "components/buttons/borderbutton/BorderButton";

import MicIcon from "@mui/icons-material/Mic";
import MicOffIcon from "@mui/icons-material/MicOff";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

import voice from "utils/VoiceFunc";
import eetch from "apis/eetch";

export default function VoiceSection({ props: { moveToEnd, updateConvs, id, background, setError } }) {
    const [onRecord, setOnRecord] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);
    const [next, setNext] = useState("");

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

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

    const getSTT = async () => {
        const file = createFile();

        if (!file) {
            return false;
        }

        const formData = new FormData();

        formData.append("sound", file);

        const values = {};

        values.user = user;
        values.formData = formData;

        eetch
            .tokenValidation(eetch.getSimulationSTT, values, dispatch)
            .then((result) => {
                updateConvs((convs) => {
                    const updated = [
                        ...convs,
                        {
                            type: "user",
                            text: result.data.text,
                            correct: true
                        }
                    ];

                    return updated;
                });
                setNext(result.data.text);
            })
            .catch(() => {
                setError(true);
            });
    };

    const getNext = async () => {
        const values = {};

        values.user = user;
        values.background = background.en;
        values.message = next;
        values.id = id;

        eetch
            .tokenValidation(eetch.nextSimulation, values, dispatch)
            .then((result) => {
                updateConvs((convs) => {
                    const updated = [...convs];

                    if (result.data.correct) {
                        updated.push({
                            type: "chatbot",
                            text: result.data.choices[0].message.content
                        });
                    } else {
                        updated[updated.length - 1].correct = false;
                    }

                    return updated;
                });

                setNext("");
            })
            .catch(() => {
                setError(true);
            });
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

        getSTT();

        setOnUpdate(false);
    }, [onUpdate]);

    useEffect(() => {
        moveToEnd();
    }, [onRecord]);

    useEffect(() => {
        if (!next) {
            return;
        }

        getNext();
    }, [next]);

    const onClickExit = useCallback(() => {
        navigate("/main");
    }, []);

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

                <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)", callback: onClickExit }}>
                    <ExitToAppIcon />
                    <span>종료</span>
                </BorderButton>
            </s.ButtonWrapper>
        </s.Section>
    );
}
