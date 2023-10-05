import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router";

import * as s from "./style";

import VoiceModal from "components/modals/voicemodal/VoiceModal";
import FilledButton from "components/buttons/filledbutton/FilledButton";
import BorderButton from "components/buttons/borderbutton/BorderButton";
import FeedbackModal from "components/modals/feedbackmodal/FeedbackModal";
import LoadingModal from "components/modals/loadingmodal/LoadingModal";

import MicIcon from "@mui/icons-material/Mic";
import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import FeedbackIcon from "@mui/icons-material/Feedback";
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
    const [feedbackList, setFeedbackList] = useState([]);
    const [onFeedback, setOnFeedback] = useState(false);
    const [loading, setLoading] = useState(false);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    function findLCS(X, Y) {
        const m = X.length;
        const n = Y.length;

        // LCS의 길이를 저장할 DP 테이블 생성
        const dp = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));

        // LCS의 길이를 계산하는 DP 과정
        for (let i = 1; i <= m; i++) {
            for (let j = 1; j <= n; j++) {
                if (X[i - 1] === Y[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // LCS를 복원
        let lcsLength = dp[m][n];
        const lcs = new Array(lcsLength);

        let i = m;
        let j = n;

        while (i > 0 && j > 0) {
            if (X[i - 1] === Y[j - 1]) {
                lcs[--lcsLength] = X[i - 1];
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.join("").replaceAll(" ", "");
    }

    const checkProun = useCallback(() => {
        if (!problem.convert || !audioText) {
            return [];
        }

        const userWords = audioText.replaceAll(" ", "").split("");
        const matchWords = findLCS(problem.convert, audioText).split("");

        let matchIndex = 0;

        const result = userWords.reduce((acc, cur) => {
            if (cur === matchWords[matchIndex]) {
                matchIndex += 1;

                acc.push({
                    word: cur,
                    match: true
                });
            } else {
                acc.push({
                    word: cur,
                    match: false
                });
            }

            return acc;
        }, []);

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

        setLoading(true);

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
                setFeedbackList(result.data.pronImgDtoList);
                setLoading(false);
            })
            .catch(() => {
                setError(true);
                setLoading(false);
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
                    <s.FeedbackButton
                        visible={feedbackList.length !== 0}
                        onClick={() => {
                            setOnFeedback(true);
                        }}
                    >
                        <FeedbackIcon />
                    </s.FeedbackButton>
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
                                setFeedbackList([]);
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
                <FeedbackModal props={{ onFeedback, setOnFeedback, feedbackList }} />
                {loading ? <LoadingModal /> : <></>}
            </s.Section>
        </>
    );
}
