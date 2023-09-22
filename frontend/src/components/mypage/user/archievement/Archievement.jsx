import React from "react";
import * as a from "./style";
import { MoreButton } from "components/mypage/user/style";

import Emoji from "assets/image/emoji/emoji0.png";
import ArrowCircleRightOutlinedIcon from "@mui/icons-material/ArrowCircleRightOutlined";

export default function Archievement({ getMore }) {
    return (
        <>
            <a.ArchieveBox>
                <a.ArchieveEmoji src={Emoji} />
                <a.ArchieveText>6+</a.ArchieveText>
            </a.ArchieveBox>
            <MoreButton onClick={() => getMore(3)}>
                달성
                <ArrowCircleRightOutlinedIcon />
            </MoreButton>
        </>
    );
}
