import React from "react";

import * as a from "./style";
import { MoreButton } from "components/mypage/user/style";

import Emoji from "assets/image/emoji/emoji0.png";
import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Archievement({ setMore, mount = 0 }) {
    return (
        <>
            <a.ArchieveBox>
                <a.ArchieveEmoji src={Emoji} />
                <a.ArchieveText>{mount === 0 ? "-" : mount + "+"}</a.ArchieveText>
            </a.ArchieveBox>
            <MoreButton onClick={() => setMore(3)}>
                달성
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
