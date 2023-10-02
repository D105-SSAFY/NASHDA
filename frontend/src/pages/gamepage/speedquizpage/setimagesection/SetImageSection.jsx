import BorderButton from "components/buttons/borderbutton/BorderButton";
import * as s from "./style";

import VolumeUpIcon from "@mui/icons-material/VolumeUp";
import RedoIcon from "@mui/icons-material/Redo";

export default function SetImageSection({ props: { problem } }) {
    if (!problem.answer) {
        return;
    }

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
                {problem.img.map((image, idx) => {
                    return (
                        <li key={idx}>
                            <s.ImageButton>
                                <img src={image} alt="" />
                            </s.ImageButton>
                        </li>
                    );
                })}
            </s.ImageList>
            <s.ButtonWrapper>
                <BorderButton props={{ color: "rgba(68, 71, 90, 0.7)" }}>
                    <RedoIcon />
                    <span>다음</span>
                </BorderButton>
            </s.ButtonWrapper>
        </s.Section>
    );
}
