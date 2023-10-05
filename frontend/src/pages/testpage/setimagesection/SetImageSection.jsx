import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import * as s from "./style";

import BorderButton from "components/buttons/borderbutton/BorderButton";

import RedoIcon from "@mui/icons-material/Redo";

import eetch from "apis/eetch";

export default function SetImageSection({ props: { problem, getNextProblem, setCorrect, testIndex, problemIndex, setError } }) {
    const [imgList, setImgList] = useState([]);
    const [answer, setAnswer] = useState(0);

    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();

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
                <p>{problem.answer}</p>
            </s.ListenWrapper>
            <s.ImageList>
                {imgList.map((image, idx) => {
                    return (
                        <li key={idx}>
                            <s.ImageButton
                                onClick={async () => {
                                    const sound = new File(["dummy"], "dummy.wav", {
                                        lastModified: new Date().getTime(),
                                        type: "audio/wav"
                                    });

                                    const formData = new FormData();

                                    formData.append("sound", sound);
                                    formData.append("index", testIndex);
                                    formData.append("order", problemIndex + 1);
                                    formData.append("imgUrl", image);

                                    const values = {};

                                    values.user = user;
                                    values.formData = formData;

                                    eetch
                                        .tokenValidation(eetch.weekSubmit, values, dispatch)
                                        .then((result) => {
                                            console.log(result);
                                        })
                                        .catch(() => {
                                            setError(true);
                                        });

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
