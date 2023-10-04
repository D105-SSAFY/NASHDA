import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import VoiceModal from "components/modals/voicemodal/VoiceModal";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";

import MicIcon from "@mui/icons-material/Mic";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import RedoIcon from "@mui/icons-material/Redo";
import ExitToAppIcon from "@mui/icons-material/ExitToApp";

import voice from "utils/VoiceFunc";
import eetch from "apis/eetch";

const diffWord = {
    단어: "word",
    단락: "phase",
    단순절: "simple",
    복합절: "complex"
};

export default function PronunciationSection({ props: { problem, diff, setUpdate, setError } }) {
    const [audioText, setAudioText] = useState("");
    const [onModal, setOnModal] = useState(false);
    const [onUpdate, setOnUpdate] = useState(false);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    // const findIncorrectString = () => {
    //     // if (!problem.convert || !audioText) {
    //     //     return [];
    //     // }

    //     // const pronunciationWords = problem.convert.replaceAll(" ", "").split("");
    //     // const userWords = audioText.replaceAll(" ", "").split("");

    //     const pronunciationWords = "자전거 타는 남자 아이".replaceAll(" ", "").split("");
    //     const userWords = "자전거 타우는 남자 아이".replaceAll(" ", "").split("");

    //     console.log(pronunciationWords, userWords);

    //     const m = pronunciationWords.length;
    //     const n = userWords.length;

    //     let maxLen = 0;
    //     let endIndex = 0;

    //     console.log(m, n);

    //     const dp = new Array(m + 1);

    //     for (let index = 0; index <= m; index++) {
    //         dp[index] = new Array(n + 1).fill(0);
    //     }

    //     console.log(dp);

    //     for (let i = 1; i <= m; i++) {
    //         for (let j = 1; j <= n; j++) {
    //             if (pronunciationWords[i - 1] === userWords[j - 1]) {
    //                 dp[i][j] = dp[i - 1][j - 1] + 1;

    //                 if (dp[i][j] > maxLen) {
    //                     maxLen = dp[i][j];
    //                     endIndex = i - 1;
    //                 }
    //             } else {
    //                 dp[i][j] = 0;
    //             }
    //         }
    //     }

    //     console.log(endIndex, maxLen, endIndex - maxLen + 1);
    //     console.log(dp);

    //     // if (maxLen == 0) { // 맞는게 하나도 없음
    //     //     return new int[]{-1, -1};

    //     // } else { // 일치하는 문자열이 존재한다.

    //     //     int startIndex = endIndex - maxLen + 1; // 공통되는 문자열의 시작 인덱스

    //     //     return new int[]{startIndex, endIndex}; // 공통되는 문자열 인덱스 범위 반환
    //     // }
    // };

    const checkProun = useCallback(() => {
        if (!problem.convert || !audioText) {
            return [];
        }

        const pronunciationWords = problem.convert.replaceAll(" ", "").split("");
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
    }, [problem, audioText]);

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

    const getSTT = async () => {
        const file = createFile();

        if (!file) {
            return false;
        }

        const formData = new FormData();

        formData.append("sound", file);
        formData.append("index", problem.index);
        formData.append("type", diffWord[diff]);

        const values = {};

        values.user = user;
        values.formData = formData;

        eetch
            .tokenValidation(eetch.getProblemSTT, values, dispatch)
            .then((result) => {
                setAudioText(result.data.stt);
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

    const onClickExit = useCallback(() => {
        navigate("/main");
    }, []);

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
                        <span>{audioText ? "다시 녹음하기" : "녹음하기"}</span>
                    </FilledButton>
                    <BorderButton
                        props={{
                            color: "rgba(68, 71, 90, 0.7)",
                            callback() {
                                setUpdate(true);
                                setAudioText("");
                            }
                        }}
                    >
                        <RedoIcon />
                        <span>다음</span>
                    </BorderButton>
                    <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)", callback: onClickExit }}>
                        <ExitToAppIcon />
                        <span>종료</span>
                    </BorderButton>
                </s.ButtonWrapper>
                <VoiceModal props={{ title: "따라 읽어보세요.", content: problem.convert, visible: onModal, callback: onClickRecordOff }} />
            </s.Section>
        </>
    );
}
