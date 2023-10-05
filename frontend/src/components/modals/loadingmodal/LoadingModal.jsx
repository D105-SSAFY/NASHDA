import { useEffect, useRef, useState } from "react";

import * as s from "./style";

const texts = ["발음을 분석하는 중 입니다...", "훌륭한 발음이에요!", "아나운서 하셔도 되겠는데요?"];

export default function LoadingModal() {
    const [index, setIndex] = useState(0);
    const timerRef = useRef(null);

    useEffect(() => {
        timerRef.current = setInterval(() => {
            setIndex(Math.floor(Math.random() * texts.length));
        }, 2000);

        return () => {
            if (timerRef.current) {
                clearInterval(timerRef.current);
            }
        };
    }, []);

    return (
        <s.Wrapper>
            <s.Section>
                <header>
                    <h3>로딩</h3>
                </header>
                <s.Loading>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </s.Loading>
                <s.Text>{texts[index]}</s.Text>
            </s.Section>
        </s.Wrapper>
    );
}
