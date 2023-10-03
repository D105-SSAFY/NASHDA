import { useEffect, useState } from "react";
import * as s from "./style";

import BorderButton from "components/buttons/borderbutton/BorderButton";

import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import RedoIcon from "@mui/icons-material/Redo";

export default function SetImageSection({ props: { problem, getNextProblem, setCorrect } }) {
    const [imgList, setImgList] = useState([]);
    const [answer, setAnswer] = useState(0);

    useEffect(() => {
        if (!problem.answer) {
            return;
        }

        const list = problem.img.reduce((acc, cur, idx) => {
            if (!cur) {
                return acc;
            }

            if (idx === 0) {
                return acc;
            }

            acc.push(cur);

            return acc;
        }, []);

        const index = Math.floor(Math.random() * 4);

        list.splice(index, 0, problem.img[0]);

        setImgList([...list]);
        setAnswer(index);
    }, [problem]);

    if (!problem.answer) {
        return;
    }

    const checkCorrect = (index) => {
        if (index === answer) {
            setCorrect((correct) => correct + 1);
        }
    };

    return (
        <s.Section>
            <header>
                <h2>그림 맞추기 영역</h2>
            </header>
            <s.ListenWrapper>
                <p>단어를 듣고 적절한 그림을 고르세요.</p>
                <s.SpeakButton>
                    {/* <span>단어 듣기</span> */}
                    <VolumeUpIcon />
                </s.SpeakButton>
            </s.ListenWrapper>
            <s.ImageList>
                {imgList.map((image, idx) => {
                    return (
                        <li key={idx}>
                            <s.ImageButton
                                onClick={() => {
                                    checkCorrect(idx);
                                    getNextProblem();
                                }}
                            >
                                <img src={image} alt="" />
                            </s.ImageButton>
                        </li>
                    );
                })}
            </s.ImageList>
            <s.ButtonWrapper>
                <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)", callback: getNextProblem }}>
                    <RedoIcon />
                    <span>다음</span>
                </BorderButton>
            </s.ButtonWrapper>
        </s.Section>
    );
}
